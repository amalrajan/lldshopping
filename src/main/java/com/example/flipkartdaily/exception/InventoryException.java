package com.example.flipkartdaily.exception;

public class InventoryException extends RuntimeException {

    private final ErrorCode errorCode;

    public InventoryException(String message) {
        super(message);
        this.errorCode = ErrorCode.GENERAL_ERROR;
    }

    public InventoryException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public enum ErrorCode {
        ITEM_NOT_FOUND,
        DUPLICATE_ITEM,
        INVALID_QUANTITY,
        INVALID_PRICE,
        GENERAL_ERROR
    }
}