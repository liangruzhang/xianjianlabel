package com.barcode.xml;


/**
 * An exception used to indicate problem encounterd during importing or
 * exporting XML documents.
 */
public class XMLException extends SystemException {

    public XMLException() {
        super();
    }

    public XMLException(String msg) {
        super(msg);
    }

    public XMLException(Exception chainedException) {
        super(chainedException);
    }

    public XMLException(String msg, Exception chainedException) {
        super(msg, chainedException);
    }

}
