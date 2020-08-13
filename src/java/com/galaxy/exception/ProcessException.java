package com.galaxy.exception;

/**
 * Created by chandra on 17-08-2020.
 */
public class ProcessException extends GalaxyException {
    public ProcessException() {
    }

    public ProcessException(String s) {
        super(s);
    }

    public ProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessException(Throwable cause) {
        super(cause);
    }
}
