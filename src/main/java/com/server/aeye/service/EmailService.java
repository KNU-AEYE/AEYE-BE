package com.server.aeye.service;

import com.server.aeye.util.SendGridUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final SendGridUtil sendGridUtil;

    public void sendEmail() throws IOException {
        sendGridUtil.sendEmail();
    }
}
