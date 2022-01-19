package com.ssafy.core.exception;

public class FileSizeException extends RuntimeException {
    public FileSizeException(String msg, Throwable t) {
        super(msg, t);
    }

    public FileSizeException(String msg) {
        super(msg);
    }

    public FileSizeException() {
        super();
    }
}
