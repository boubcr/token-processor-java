package com.token.topup.processor.execptions;

public class WriteFileException extends RuntimeException {
    public WriteFileException(String message) {
        super(message);
    }
}
