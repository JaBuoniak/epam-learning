package jmp.dto;

import java.time.LocalDate;

public class Subscription {
    private final String bankcard;
    private final LocalDate startDate;

    public Subscription(String bankcard) {
        this.bankcard = bankcard;
        startDate = LocalDate.now();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getBankcard() {
        return bankcard;
    }
}
