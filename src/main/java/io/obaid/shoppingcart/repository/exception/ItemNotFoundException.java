package io.obaid.shoppingcart.repository.exception;

/**
 * An exception that is thrown when an item cannot be found.
 */
public class ItemNotFoundException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Item not found";

    public ItemNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
