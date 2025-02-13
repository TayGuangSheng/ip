public class WagErrorHandler {
    public static void handleError(WagException e) {
        System.out.println("____________________________________________________________");
        System.out.println(" OOPS!!! " + e.getMessage());
        System.out.println("____________________________________________________________");
    }
}
