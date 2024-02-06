package com.smallworld.Exception;

public class TransactionLoaderException extends RuntimeException {
    public TransactionLoaderException(String message) {
        super(message);
    }

    public TransactionLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
