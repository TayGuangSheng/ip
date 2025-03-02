package wag.exceptions;

/**
 * Custom exception class for handling application-specific errors.
 */
public class WagException extends Exception {

    /**
     * Constructs a new WagException with the specified error message.
     *
     * @param message The error message to be displayed.
     */
    public WagException(String message) {
        super(message);
    }
}
