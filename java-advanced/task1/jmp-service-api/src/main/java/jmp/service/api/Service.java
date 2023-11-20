package jmp.service.api;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

public interface Service {
    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    default double getAverageUsersAge() {
        LocalDate now = LocalDate.now();
        return getAllUsers().stream()
                .map(User::getBirthday)
                .map(birthday -> Period.between(birthday, now))
                .mapToLong(Period::getYears)
                .average().orElse(0.0);
    }
}
