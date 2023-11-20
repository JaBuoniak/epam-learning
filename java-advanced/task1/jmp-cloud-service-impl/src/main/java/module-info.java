module jmp.cloud.service.impl {
    exports jmp.cloud.service;
    requires transitive jmp.service.api;
    requires jmp.dto;
    provides jmp.service.api.Service with jmp.cloud.service.ServiceImpl;
}