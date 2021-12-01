package com.hyeonjun.scm.domain.errors;

public class NoEntityException extends RuntimeException {
    private ErrorCode code;

    public NoEntityException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return this.code;
    }
}
