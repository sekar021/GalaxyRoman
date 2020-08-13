package com.galaxy.exception;

/**
 * Created by chandra on 17-08-2020.
 */
public class UnknownUnitException extends GalaxyException {
    public UnknownUnitException() {
    }

    public UnknownUnitException(String s) {
        super(s);
    }

    public UnknownUnitException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownUnitException(Throwable cause) {
        super(cause);
    }
}
