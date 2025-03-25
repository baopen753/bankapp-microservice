package org.baopen753.accounts.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String object, String identifier, Object value) {
        super(String.format("Object %s with identifier %s not found with value: %s", object, identifier, value));
    }
}
