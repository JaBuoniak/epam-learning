package jmp.bank.api;

public interface Bank {
    BankCard createBankCard(User user, BankCardType type);
}
