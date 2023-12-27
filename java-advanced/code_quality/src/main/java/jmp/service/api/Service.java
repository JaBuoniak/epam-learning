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
    public void subscribe(BankCard bankCard);

    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    public List<User> getAllUsers();

    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);

    @SuppressWarnings("squid:S1612")
    public default double getAverageUsersAge() {
        LocalDate now = LocalDate.now();
        return getAllUsers().stream()
                .map(user -> user.getBirthday())
                .map(birthday -> Period.between(birthday, now))
                .mapToLong(period -> period.getYears())
                .average().orElse(0.0);
    }

    public static boolean isPayableUser(User user) {
        return Period.between(user.getBirthday(), LocalDate.now()).getYears() >= 18;
    }

}
