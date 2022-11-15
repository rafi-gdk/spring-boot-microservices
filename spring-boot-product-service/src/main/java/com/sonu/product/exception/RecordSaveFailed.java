package com.sonu.product.exception;

public class RecordSaveFailed extends RuntimeException {
    public RecordSaveFailed(String message) {
        super(message);
    }
}
