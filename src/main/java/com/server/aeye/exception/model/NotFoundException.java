package com.server.aeye.exception.model;

import com.server.aeye.exception.ErrorStatus;

public class NotFoundException extends CustomException {

        public NotFoundException(ErrorStatus errorStatus, String message) {
            super(errorStatus, message);
        }

}
