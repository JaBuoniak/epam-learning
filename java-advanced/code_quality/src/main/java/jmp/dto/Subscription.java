package jmp.dto;

import java.time.LocalDate;

@SuppressWarnings("squid:S1124")
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
