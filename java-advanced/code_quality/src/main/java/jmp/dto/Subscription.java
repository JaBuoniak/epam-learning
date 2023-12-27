package jmp.dto;

import java.time.LocalDate;

public class Subscription {
    final private String bankcard;
    final private LocalDate startDate;

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
