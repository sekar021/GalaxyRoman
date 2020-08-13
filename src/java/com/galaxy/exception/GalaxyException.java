package com.galaxy.exception;

/**
 * Created by chandra on 17-08-2020.
 */
public class GalaxyException extends IllegalArgumentException {
    public GalaxyException() {
    }

    public GalaxyException(String s) {
        super(s);
    }

    public GalaxyException(String message, Throwable cause) {
        super(message, cause);
    }

    public GalaxyException(Throwable cause) {
        super(cause);
    }
}
