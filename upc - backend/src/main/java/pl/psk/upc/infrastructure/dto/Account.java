package pl.psk.upc.infrastructure.dto;

import jakarta.persistence.Id;

import java.util.UUID;

public interface Account {
    UUID getUuid();
    String getEmail();
    String getPassword();
    String getRoles();

}