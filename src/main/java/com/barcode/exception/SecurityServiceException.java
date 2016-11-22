package com.barcode.exception;


/**
 * General purpose security service exception.  More specific security
 * exceptions can be defined as subclasses of this exception class.
 */
public class SecurityServiceException extends ApplicationException {

    /**
     * Constructs a security service exception.
     * @param   msg the exception message
     */
    public SecurityServiceException(String msg) {
        super(msg);
    }

    /**
     * Constructs a security service exception.
     * @param   chaniedException the chained exception
     */
    public SecurityServiceException(Exception chainedException) {
        super(chainedException);
    }

    /**
     * Constructs a security service exception.
     * @param   msg the exception message
     * @param   chaniedException the chained exception
     */
    public SecurityServiceException(String msg, Exception chainedException) {
        super(msg, chainedException);
    }

}