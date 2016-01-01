package org.dw.decision_variable_expander;

/**
 * Thrown to indicate there was an exception when calling the get() method of a
 * Guice Provider implementation.
 */
class ProviderException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProviderException() {
        super();
    }

    public ProviderException(String message) {
        super(message);
    }

    public ProviderException(Throwable cause) {
        super(cause);
    }

    public ProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
