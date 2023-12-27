package jmp.cloud.service;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.service.api.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("squid:1124")
public class ServiceImpl implements Service {
    final private List<BankCard> cardsRepository;
    final private List<Subscription> subscriptionsRepository;

    @SuppressWarnings("squid:2293")
    public ServiceImpl() {
        this.cardsRepository = new ArrayList<BankCard>();
        this.subscriptionsRepository = new ArrayList<Subscription>();
    }

    @Override
    public void subscribe(BankCard bankCard) {
        cardsRepository.add(bankCard);
        subscriptionsRepository.add(new Subscription(bankCard.getNumber()));
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return subscriptionsRepository.stream().filter(s -> cardNumber.equals(s.getBankcard())).findFirst();
    }

    @Override
    @SuppressWarnings({"squid:S6204", "squid:S1612"})
    public List<User> getAllUsers() {
        return cardsRepository.stream().map(card -> card.getUser()).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        return subscriptionsRepository.stream().filter(condition).toList();
    }
}
