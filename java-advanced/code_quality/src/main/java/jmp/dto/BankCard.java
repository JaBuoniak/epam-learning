package jmp.dto;

import java.time.Instant;

public class BankCard {
    private final String number;
    private final User user;

    public BankCard(User user) {
        this.user = user;
        number = generateCardNumber();
    }

    protected String generateCardNumber() {
        return String.valueOf(Instant.now().getEpochSecond());
    }

    public String getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }
}
