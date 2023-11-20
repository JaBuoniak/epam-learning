module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    provides jmp.service.api.Service with jmp.cloud.service.ServiceImpl;
}