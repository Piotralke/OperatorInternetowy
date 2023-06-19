package pl.psk.upc.application.security;

import java.util.UUID;

public interface Account {
    UUID getUuid();
    String getEmail();
    String getPassword();
    String getRoles();

}