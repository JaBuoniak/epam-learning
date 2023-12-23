package jmp.service.api;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {
    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);

    default double getAverageUsersAge() {
        LocalDate now = LocalDate.now();
        return getAllUsers().stream()
                .map(User::getBirthday)
                .map(birthday -> Period.between(birthday, now))
                .mapToLong(Period::getYears)
                .average().orElse(0.0);
    }

    static boolean isPayableUser(User user) {
        return Period.between(user.getBirthday(), LocalDate.now()).getYears() >= 18;
    }

}
