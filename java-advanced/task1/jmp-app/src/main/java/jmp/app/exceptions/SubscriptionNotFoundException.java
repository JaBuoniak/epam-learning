package jmp.app.exceptions;

public class SubscriptionNotFoundException extends Throwable {
    @Override
    public String getMessage() {
        return "Could not find any subscription for provided number.";
    }
}
