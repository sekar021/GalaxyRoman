package com.galaxy.exception;

/**
 * Created by chandra on 17-08-2020.
 */
public class InputReadException extends GalaxyException {
    public InputReadException() {
    }

    public InputReadException(String s) {
        super(s);
    }

    public InputReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputReadException(Throwable cause) {
        super(cause);
    }
}
