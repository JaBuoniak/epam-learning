package jmp.bank.api;

import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.User;

public interface Bank {
    public BankCard createBankCard(User user, BankCardType type);
}
