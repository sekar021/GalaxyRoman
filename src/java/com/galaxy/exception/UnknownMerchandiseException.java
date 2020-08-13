package com.galaxy.exception;

/**
 * Created by chandra on 17-08-2020.
 */
public class UnknownMerchandiseException extends GalaxyException {
    public UnknownMerchandiseException() {
    }

    public UnknownMerchandiseException(String s) {
        super(s);
    }

    public UnknownMerchandiseException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownMerchandiseException(Throwable cause) {
        super(cause);
    }
}
