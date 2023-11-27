package jmp.dto;

public class CreditBankCard extends BankCard {
    public CreditBankCard(User user) {
        super(user);
    }

    @Override
    protected String generateCardNumber() {
        return new StringBuilder(super.generateCardNumber()).reverse().toString();
    }
}
