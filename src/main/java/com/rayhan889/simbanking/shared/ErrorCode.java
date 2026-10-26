package com.rayhan889.simbanking.shared;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, false),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, false),
    INSUFFICIENT_FUNDS(HttpStatus.UNPROCESSABLE_CONTENT, false),
    ACCOUNT_NOT_ACTIVE(HttpStatus.UNPROCESSABLE_CONTENT, false),
    CURRENCY_MISMATCH(HttpStatus.UNPROCESSABLE_CONTENT, false),
    SAME_ACCOUNT(HttpStatus.UNPROCESSABLE_CONTENT, false);

    private final HttpStatus status;
    private final boolean retryable;

    ErrorCode(HttpStatus status, boolean retryable) {
        this.status = status;
        this.retryable = retryable;
    }

    public String code() {
        return name();
    }

    public HttpStatus status() {
        return status;
    }

    public boolean retryable() {
        return retryable;
    }
}
