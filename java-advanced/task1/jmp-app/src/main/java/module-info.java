module jmp.app {
    uses jmp.cloud.service.ServiceImpl;
    uses jmp.cloud.bank.BankImpl;
    requires transitive jmp.cloud.bank.impl;
    requires transitive jmp.cloud.service.impl;
    requires jmp.dto;
}