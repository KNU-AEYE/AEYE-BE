package com.server.aeye.util;

import com.server.aeye.exception.ErrorStatus;
import com.server.aeye.exception.model.CustomException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AlimTalkUtil {

    private final WebClient webClient;
    private final String serviceId;
    private final String ncpAccessKey;
    private final String ncpSecretKey;
    private final String plusFriendId;

    public AlimTalkUtil(
            WebClient.Builder webClientBuilder,
            @Value("${ncp.service-id}") String serviceId,
            @Value("${ncp.access-key}") String ncpAccessKey,
            @Value("${ncp.secret-key}") String ncpSecretKey,
            @Value("${ncp.plus-friend-id}") String plusFriendId) {
        this.webClient = webClientBuilder.baseUrl("https://sens.apigw.ntruss.com").build();
        this.serviceId = serviceId;
        this.ncpAccessKey = ncpAccessKey;
        this.ncpSecretKey = ncpSecretKey;
        this.plusFriendId = plusFriendId;
    }

    public Mono<String> sendAlimTalk(String to, String templateCode, String content) {
        String alimTalkSendRequestUrl = "/alimtalk/v2/services" + serviceId + "/messages";

        // Signature 생성
        String[] signatureArray = makePostSignature(ncpAccessKey, ncpSecretKey, alimTalkSendRequestUrl);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("plusFriendId", plusFriendId);
        requestBody.put("templateCode", templateCode);

        Map<String, String> message = new HashMap<>();
        message.put("to", to);
        message.put("content", content);

        requestBody.put("messages", new Map[]{message});

        return webClient.post()
            .uri(alimTalkSendRequestUrl)
            .header("Content-Type", "application/json; charset=UTF-8")
            .header("x-ncp-iam-access-key", ncpAccessKey)
            .header("x-ncp-apigw-timestamp", signatureArray[0])
            .header("x-ncp-apigw-signature-v2", signatureArray[1])
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(String.class)
            .doOnSuccess(result -> log.info("Response: " + result))
            .doOnError(error -> log.info("Error: " + error.getMessage()));
    }

    public String[] makePostSignature(String accessKey, String secretKey, String url) {
        String[] result = new String[2];

        try {
            String timeStamp = String.valueOf(Instant.now().toEpochMilli());
            String space = " ";
            String newLine = "\n";
            String method = "POST";

            String message = method + space + url + newLine + timeStamp + newLine + accessKey;

            SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String encodeBase64String = Base64.encodeBase64String(rawHmac);

            result[0] = timeStamp;
            result[1] = encodeBase64String;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ErrorStatus.BAD_REQUEST, ErrorStatus.BAD_REQUEST.getMessage());
        }
        return result;
    }

}
