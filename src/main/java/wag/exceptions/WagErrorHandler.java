package wag.exceptions;

/**
 * Handles exceptions that occur within the application.
 */
public class WagErrorHandler {

    /**
     * Prints an error message when a {@link WagException} occurs.
     *
     * @param e The WagException containing the error message.
     */
    public static void handleError(WagException e) {
        System.out.println(" OOPS! " + e.getMessage());
        System.out.println("________________________________________________________________________");
    }
}
