package jmp.dto;

import java.time.Instant;

@SuppressWarnings("squid:S1124")
public class BankCard {
    final private String number;
    final private User user;

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
