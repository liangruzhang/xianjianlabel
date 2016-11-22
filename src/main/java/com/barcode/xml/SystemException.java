package com.barcode.xml;

import java.util.Random;

/**
 * This is the super class of all checked exception classes
 * defined in the ROSE framework.  Applications that are based
 * on the ROSE framework should also have their exception classes
 * defined as subclasses to it.  The SystemException class
 * provides an exception chaining mechanism that allows the actual
 * cause of the exception to be specified.  When the stack trace
 * of this exception is printed, the stack trace of its chained
 * exception is also printed.
 * <p>
 * SystemRuntimeException is a runtime (unchecked) version of this
 * exception class.
 *
 * @author  alan zhang
 * @version $Revision: 1.2 $
 * @see     SystemRuntimeException
 */
public abstract class SystemException extends Exception {

    private static Random random = new Random();
    private transient boolean chainedExceptionPrinted = false;

    private transient static Boolean enablePrintStackTrace = null;

    private Exception chainedException;

    /**
     * Constructs a system exception with no message and no chained
     * exception.
     */
    public SystemException() {
        this(null, null);
    }

    /**
     * Constructs a system exception with the specified message.
     * @param   msg the exception message.
     */
    public SystemException(String msg) {
        this(msg, null);
    }

    /**
     * Constructs a system exception with the specified chained
     * exception.
     * @param   chainedException    the chained exception.
     */
    public SystemException(Exception chainedException) {
        this(null, chainedException);
    }

    /**
     * Constructs a system exception with the specified message and
     * chained exception.
     *
     * @param   msg the exception message.
     * @param   chainedException the chained exception.
     */
    public SystemException(String msg, Exception chainedException) {
        super(msg == null ? "" : msg);
        this.chainedException = chainedException;
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
                    System.getProperty("rose.debug.print.system.exception.stacktrace", "true"));
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
