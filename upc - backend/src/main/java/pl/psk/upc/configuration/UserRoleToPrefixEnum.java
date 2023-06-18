package pl.psk.upc.configuration;

import lombok.Getter;
import pl.psk.upc.web.UpcRestPaths;

import java.util.List;

@Getter
public enum UserRoleToPrefixEnum {
    USER_PREFIX(UpcRestPaths.USER_PREFIX, List.of("USER", "WORKER", "ADMIN")),
    WORKER_PREFIX(UpcRestPaths.WORKER_PREFIX, List.of("WORKER", "ADMIN")),
    ADMIN_PREFIX(UpcRestPaths.ADMIN_PREFIX, List.of("ADMIN"));

    private String urlPrefix;
    private List<String> requiredRolesForPrefix;

    UserRoleToPrefixEnum(String urlPrefix, List<String> requiredRolesForPrefix) {
        this.urlPrefix = urlPrefix;
        this.requiredRolesForPrefix = requiredRolesForPrefix;
    }
}
