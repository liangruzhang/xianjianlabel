package com.barcode.exception;


/**
 * Exception which is used to indicate that a user does not exist.
 */
public class InvalidPasswordException extends SecurityServiceException {

    /**
     * Constructs the exception object.
     * @param   msg the exception message
     */
    public InvalidPasswordException(String msg) {
        super(msg);
    }
    /**
     * Constructs the exception object with the specified message, and
     * chained exception.
     * @param   msg the exception message
     * @param   chainedException the chained exception
     */
    public InvalidPasswordException(String msg, Exception chainedException) {
        super(msg, chainedException);
    }

}