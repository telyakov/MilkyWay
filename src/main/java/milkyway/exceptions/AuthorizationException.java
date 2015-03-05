package milkyway.exceptions;

public class AuthorizationException  extends Exception {
    public AuthorizationException() {
        super("You are not authorized");

    }
}
