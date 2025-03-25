package org.baopen753.accounts.exception;




public class CustomerAlreadyExistException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
