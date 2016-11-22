package com.barcode.exception;

import java.io.*;
import java.util.*;
import java.text.*;

/**
 * A data structure that contains a piece of information representing a
 * well-defined application error.  This class consists of a message code, a
 * message format pattern and an optional list of message format arguments.
 *
 * @author  alan zhang
 * @version $Revision: 1.2 $
 *
 */
public class ApplicationErrorMessage implements Serializable {

    /**
     * The message code.
     */
    private String code;

    /**
     * The message format pattern.
     */
    private String pattern;

    /**
     * The message format arguments.
     */
    private String[] arguments;

    /**
     * Construct an application error message with the specified message code,
     * and message format pattern.
     * @param   code the error message code
     * @param   pattern the format pattern for the message
     */
    public ApplicationErrorMessage(String code, String pattern) {
        this(code, pattern, null);
    }

    /**
     * Construct an application error message with the specified message code,
     * message format pattern, and the list of message arguments.
     * @param   code the error message code
     * @param   pattern the format pattern for the message
     * @param   an array of the message format argument list.
     */
    public ApplicationErrorMessage(
            String code, String pattern, String[] arguments ) {
        this.code = code;
        this.pattern = pattern;
        this.arguments = null;
        this.arguments = arguments != null ? (String[])arguments.clone() : null;
    }

    /**
     * Returns the message code of this error message.
     * @return the message code
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the message format pattern of this error message.
     * @return the message format pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Returns the message argument list of this error message.
     * @return the message argument list
     */
    public String[] getArguments() {
        return arguments != null ? (String[])arguments.clone() : null;
    }

    /**
     * Returns the formatted string for this application error message.
     * @return the formatted string
     */
    public String toString() {
        if (pattern == null) {
            return code;
        }
        return code + " - " + MessageFormat.format(pattern, arguments);
    }
}
