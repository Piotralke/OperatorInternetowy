package pl.psk.upc.infrastructure.entity;

import lombok.Getter;

@Getter
public enum ContractLengthEnum {
    TWELVE(12L),
    TWENTY_FOUR(24L);

    private final Long contractLengthAsNumber;

    ContractLengthEnum(Long contractLengthAsNumber) {
        this.contractLengthAsNumber = contractLengthAsNumber;
    }
}
