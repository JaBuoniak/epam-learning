module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;
    provides jmp.bank.api.Bank with jmp.cloud.bank.BankImpl;
}