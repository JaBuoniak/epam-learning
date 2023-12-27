package jmp.app.exceptions;

@SuppressWarnings("squid:2166")
public class SubscriptionNotFoundException extends Throwable {
    @Override
    public String getMessage() {
        return "Could not find any subscription for provided number.";
    }
}
