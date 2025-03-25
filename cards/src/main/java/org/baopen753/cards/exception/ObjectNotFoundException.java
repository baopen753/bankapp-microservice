package org.baopen753.cards.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String object, String identifier, String value) {
        super(String.format("%s not found by identifier %s with value %s", object, identifier, value));
    }
}
