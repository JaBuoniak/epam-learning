module jmp.cloud.bank.impl {
    exports jmp.cloud.bank;
    requires transitive jmp.bank.api;
    requires jmp.dto;
    provides jmp.bank.api.Bank with jmp.cloud.bank.BankImpl;
}