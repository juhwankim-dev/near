package com.ssafy.core.exception;

public class FailedException extends RuntimeException {
    public FailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public FailedException(String msg) {
        super(msg);
    }

    public FailedException() {
        super();
    }
}
