package org.baopen753.loans.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String object, String identifier, Object value) {
        super(String.format("%s with identifier %s not found with value: %s", object, identifier, value));
    }
}