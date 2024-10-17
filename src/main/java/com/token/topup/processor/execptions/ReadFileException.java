package com.token.topup.processor.execptions;

public class ReadFileException extends RuntimeException {
    public ReadFileException(String message) {
        super(message);
    }
}
