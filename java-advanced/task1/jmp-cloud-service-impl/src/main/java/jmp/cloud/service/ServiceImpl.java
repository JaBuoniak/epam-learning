package jmp.cloud.service;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.service.api.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {
    private final List<BankCard> cardsRepository;
    private final List<Subscription> subscriptionsRepository;

    public ServiceImpl() {
        this.cardsRepository = new ArrayList<>();
        this.subscriptionsRepository = new ArrayList<>();
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
    public List<User> getAllUsers() {
        return cardsRepository.stream().map(BankCard::getUser).collect(Collectors.toUnmodifiableList());
    }
}
