package com.hyeonjun.scm.domain.errors;

public class FormSyntaxException extends RuntimeException {
    private ErrorCode code;

    public FormSyntaxException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return this.code;
    }
}
