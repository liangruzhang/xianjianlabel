/*
 * $Id: ApplicationException.java,v 1.14 2004/10/06 05:09:44 cheung.kwokfung Exp $
 *
 * Copyright (c) 2001 Hongkong International Terminals Limited.
 * All rights reserved.
 *
 * This file contains the valuable properties of Hongkong International
 * Terminals Limited, embodying substantial creative efforts and
 * confidential information, ideas and expressions. No part of this file
 * may be reproduced or distributed in any form or by any means, or
 * stored in a data base or a retrieval system, without the prior written
 * permission of Hongkong International Terminals Limited.
 *
 *                                 ---
 */
package com.barcode.exception;

import java.io.*;

import org.xml.sax.SAXException;

import com.barcode.xml.XMLExportWriter;
import com.barcode.xml.XMLExportable;

import java.util.Random;

/**
 * An exception which represents a exceptional situation caused by some
 * application logic.  It should be used to represent exceptional situations
 * purely related to the business aspect of the application, and should
 * not be used to represent any type of situations other this purpose.
 * <br>
 * There is an optional piece of information contained within this exception
 * object to indicate the reason why this exception is raised.  This is called
 * the "reason" attribute.  This attribute can be obtained by calling the
 * getReason() method; and it is of type java.lang.Object.  So, you can use
 * it to carry any information that makes sense to the receivers of this
 * exception, i.e. the exception handler.  The exception handler will probably
 * test the type of the reason attribute first, cast it to the actual type,
 * and then retrieve the appropriate information from it.  Subclasses of this
 * class can provide some convenient methods to retrieve information from it.
 * <br>
 * It is important that the reason attribute be serializable so that this
 * exception can be returned in a remote method call, such as in an RMI or EJB
 * call.
 * <br>
 * In addition to the "reason" attribute, this exception class also provides
 * some convenient constructors and getter methods to treat the "reason"
 * attribute as type ApplicationErrorMessage, or as an array of it.
 * The ApplicationErrorMessage class is designed specifically to represent an
 * application error message which contains some more comprehensive information
 * other simply a character string.  The information contained within an
 * application error message include the error message code, the message
 * format pattern, and a list of runtime arguments.  It is expected thos most
 * of the applications would use an application error message to indicate the
 * reason of an application exception.
 * <br>
 * Since this application exception class extends the SystemException, it
 * provides an exception chaining mechanism.  The formal type of the chained
 * exception is java.lang.Exception, so you can place any type of chained
 * exception not necessarily restricted to ApplicationException.
 *
 * @author  alan zhang
 * @version $Revision: 1.2 $
 * @see     ApplicationErrorMessage
 * @see     ApplicationRuntimeException
 *
 */
public abstract class ApplicationException extends Exception
        implements XMLExportable {

    private static Random random = new Random();
    private transient boolean chainedExceptionPrinted = false;

    private transient static Boolean enablePrintStackTrace = null;

    private Exception chainedException;

    private ApplicationErrorMessage defaultErrorMessage = null;

    /**
     * The reason of the application exception.
     */
    private Object reason;

    private void initDefaultErrorMessage(String msg) {
        if (msg == null || "".equals(msg)) {
            defaultErrorMessage =
                new ApplicationErrorMessage("", this.getClass().getName());
        } else {
            defaultErrorMessage =
                new ApplicationErrorMessage("", msg);
        }
    }

    /**
     * Constructs an empty ApplicationException.
     */
    public ApplicationException() {
        super();
        initDefaultErrorMessage(null);
    }

    /**
     * Constructs an application exception with the specified message.
     * @param   msg     the message
     */
    public ApplicationException(String msg) {
        super(msg);
        initDefaultErrorMessage(msg);
    }

    /**
     * Constructs an application exception with the specified message,
     * and chained exception.
     * @param   msg     the message
     * @param   chainedException the chained exception
     */
    public ApplicationException(String msg, Exception chainedException) {
        this(msg, null, chainedException);
        initDefaultErrorMessage(msg);
    }

    /**
     * Constructs an application exception with the specified chained exception.
     * @param   chainedException the chained exception
     */
    public ApplicationException(Exception chainedException) {
        this(null, null, chainedException);
    }

    /**
     * Constructs an application exception with the specified message,
     * reason, and chained exception.
     * @param   msg     the message
     * @param   reason  the exception reason
     * @param   chainedException the chained exception
     */
    public ApplicationException(
            String msg, Object reason, Exception chainedException) {
        super(msg == null ? "" : msg);
        this.chainedException = chainedException;
        this.reason = reason;
        initDefaultErrorMessage(msg);
    }

    /**
     * Constructs an application exception with the specified application
     * error message.
     * @param   message  the error message
     */
    public ApplicationException(ApplicationErrorMessage message) {
        this(null, message, null);
    }

    /**
     * Constructs an application exception with the specified application
     * error message, and the chained exception.
     * @param   message the application error message
     * @param   chainedException the chained exception
     */
    public ApplicationException(
            ApplicationErrorMessage message, Exception chainedException) {
        this(null, message, chainedException);
    }

    /**
     * Constructs an application exception with the specified array of
     * application error messages.
     * @param   messages an array of the application error messages
     */
    public ApplicationException(ApplicationErrorMessage[] messages) {
        this(null, messages, null);
    }

    /**
     * Constructs an application exception with the specified array of
     * application error messages, and the chained exception.
     * @param   messages an array of the application error messages
     * @param   chainedException the chained exception
     */
    public ApplicationException(
            ApplicationErrorMessage[] messages, Exception chainedException) {
        this(null, messages, chainedException);
    }

    /**
     * Returns the reason associated with this application exceptioni.  It can
     * be null, i.e. no specific reason has been specified for the exception.
     * Since the return value is of type Object, interpretation of it may need
     * type testing and casting in order to obtain the information from it.
     * @return  the reason attribute; it can be null to mean no specific reason
     *          specified.
     */
    public Object getReason() {
        return reason;
    }

    /**
     * Returns the application error message associated with this application
     * exception.  This is a convenient method for getReason() with type
     * casting of the "reason" object to ApplicationErrorMessage done
     * automatically.  If the type of the "reason" object is
     * ApplicationErrorMessage, the application error message is returne.  if
     * the type of "reason" attribute is an array of ApplicationErrorMessage,
     * the first element of the array is returned; otherwise null is returned.
     * @return the application error message if the type of "reason" is
     *          ApplicationErrorMessage, or the first element of an array
     *          if the type of "reason" is an array of ApplicationErrorMessage;
     *          otherwise null is returned.
     */
    public ApplicationErrorMessage getErrorMessage() {
        if (reason instanceof ApplicationErrorMessage) {
            return (ApplicationErrorMessage)reason;
        } else
        if (reason instanceof ApplicationErrorMessage[]) {
            ApplicationErrorMessage[] messages = (ApplicationErrorMessage[])reason;
            if (messages.length > 0) {
                return messages[0];
            } else {
                return this.defaultErrorMessage;
            }
        } else {
            return this.defaultErrorMessage;
        }
    }

    /**
     * Returns an array of application error messages associated with this
     * application exception.  This is a convenient method of getReason() with
     * type casting of the "reason" attribute to ApplicationErrorMessage[] done
     * automatically.  If the type of the "reason" attribute is
     * ApplicationErrorMessage[], the exact array is returned; if the type of
     * the "reason" attribute is simply ApplicationErrorMessage, an array of
     * one element of ApplicationErrorMessage is returned; otherwose, null is
     * returned.
     * @return an array of application error messages; null is returned if the
     *          "reason" attribute is nether an ApplicaitonErrorMessage nor an
     *          array of ApplicatioinErrorMessage.
     */
    public ApplicationErrorMessage[] getErrorMessages() {
        if (reason instanceof ApplicationErrorMessage[]) {
            return (ApplicationErrorMessage[])reason;
        } else
        if (reason instanceof ApplicationErrorMessage) {
            return new ApplicationErrorMessage[] { (ApplicationErrorMessage)reason };
        } else {
            return new ApplicationErrorMessage[] { this.defaultErrorMessage };
        }
    }

    /**
     * Returns a string representation of this exception.  It concatinates
     * the string representation of the chained exception if there is any.<br>
     * If the reason object is ApplicationErrorMessage or array of
     * ApplicationErrorMessage, it will transform it into the string
     * represenetation.
     * @return  the string representation of this exception
     */
    public String toString() {
        ApplicationErrorMessage[] messages = getErrorMessages();
        if (messages == null) {
            return super.toString();
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append(getClass().getName() + " - ");
            for (int i=0; i < messages.length; i++) {
                sb.append("[");
                sb.append(messages[i].toString());
                sb.append("]");
            }
            return sb.toString();
        }
    }

    public void writeXML(XMLExportWriter writer, String name)
                    throws SAXException {
        name = (name == null) ? "exception" : name ;
        writer.startElement(name);

        writer.writeElement("runtime", "");
        writer.writeElement("class", this.getClass().getName());
        ApplicationErrorMessage[] messages = this.getErrorMessages();
        if ((messages == null) || (messages.length == 0)) {
            writer.writeElement("message", this.getMessage());
        } else {
            for (int i=0; i < messages.length; i++) {
                writer.writeElement("message", messages[i].toString());
            }
        }

        Exception e = this.getChainedException();
        if (e != null) {
            if (e instanceof ApplicationException) {
                ((ApplicationException)e).writeXML(writer, "exception");
            } else {
                writer.startElement("exception");
                writer.writeElement("runtime", "");
                writer.writeElement("class", e.getClass().getName());
                writer.writeElement("message", e.getMessage());
                writer.endElement("exception");
            }
        }
        writer.endElement(name);
    }

    public String getMessage() {
        // TODO: consider returning a message based on the value of reason if
        // it is an application error message instead of inheriting from the
        // superclass.
        return super.getMessage();
    }

    /**
     * Returns the exception chained to this exception.
     * @return  the chained exception;
     */
    public Exception getChainedException() {
        return chainedException;
    }

    /**
     * To get the setting of print stack trace, default is true
     * @return
     */
    public static boolean getEnablePrintStackTrace() {
        if (enablePrintStackTrace == null) {
            enablePrintStackTrace = Boolean.valueOf(
                    System.getProperty("rose.debug.print.application.exception.stacktrace", "true"));
        }
        return enablePrintStackTrace.booleanValue();
    }


    public static void setEnablePrintStackTrace(boolean enabled) {
        enablePrintStackTrace = new Boolean(enabled);
    }

    /**
     * Prints this exception and its stack trace to System.err.
     * If the exception has a chained exception, the stack trace of
     * the chained exception is also printed.
     */
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    /**
     * Prints this exception and its stack trace to the specified
     * print stream.
     * If the exception has a chained exception, the stack trace of
     * the chained exception is also printed.
     * @param   s the print stream to print to.
     */
    public void printStackTrace(java.io.PrintStream s) {
        printStackTrace(new java.io.PrintWriter(s));
    }

    /**
     *Prints this exception and it stack trace to the specified
     *print writer.
     * If the exception has a chained exception, the stack trace of
     * the chained exception is also printed.
     * @param   s the print writer to print to.
     */
    public void printStackTrace(java.io.PrintWriter s) {
        if (getEnablePrintStackTrace()) {
            int id = random.nextInt();
            int oid = System.identityHashCode(this);
            s.println("[" + id + "] Begin stack trace of: " + oid);

            super.printStackTrace(s);
            if (chainedException != null) {
                s.println("Chained Exception:");
                if (chainedExceptionPrinted) {
                    s.println("    (already printed) Exception: " + chainedException.getMessage());
                } else {
                    chainedExceptionPrinted = true;
                    chainedException.printStackTrace(s);
                }
            }

            s.println("[" + id + "] End stack trace of: " + oid);
        } else {
            StringBuffer sb = new StringBuffer(300);
            sb.append(this.getClass().getName());
            sb.append(": ");
            String errorMsg = this.getMessage();
            if (errorMsg == null) {
                sb.append("null");
            } else {
                sb.append(errorMsg.substring(0, Math.min(200, errorMsg.length())));
            }
            s.println(sb.toString());
            s.println("<< stack trace truncated >>");
        }
        s.flush();
    }


}
