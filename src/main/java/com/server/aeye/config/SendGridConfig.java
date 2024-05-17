package com.server.aeye.config;

import com.sendgrid.SendGrid;
import com.server.aeye.exception.ErrorStatus;
import com.server.aeye.exception.model.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfig {

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @Value("${sendgrid.integration.enabled}")
    private boolean isSendGridIntegrationEnabled = false;

    @Bean
    public SendGrid sendGrid() {
        if (!isSendGridIntegrationEnabled && apiKey == null) {
            throw new CustomException(ErrorStatus.BAD_REQUEST, "SendGrid integration is not enabled");
        }
        return new SendGrid(apiKey);
    }
}
