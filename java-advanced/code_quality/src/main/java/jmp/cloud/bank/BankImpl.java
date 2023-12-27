package jmp.cloud.bank;

import jmp.bank.api.Bank;
import jmp.dto.*;

public class BankImpl implements Bank {
    @Override
    @SuppressWarnings("squid:4524")
    public BankCard createBankCard(User user, BankCardType type) {
        switch (type) {
            default:
                return null;
            case CREDIT:
                return new CreditBankCard(user);
            case DEBIT:
                return new DebitBankCard(user);
        }
    }
}
