package com.galaxy.exception;

/**
 * Created by chandra on 17-08-2020.
 */
public class OutputWriteException extends GalaxyException {
    public OutputWriteException() {
    }

    public OutputWriteException(String s) {
        super(s);
    }

    public OutputWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutputWriteException(Throwable cause) {
        super(cause);
    }
}
