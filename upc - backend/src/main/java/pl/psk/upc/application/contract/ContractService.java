package pl.psk.upc.application.contract;

import pl.psk.upc.web.contract.ContractDto;

import java.util.UUID;

public interface ContractService {
    ContractDto addNewPaymentToContract(UUID contractUuid, double paymentAmount);
    ContractDto findByUuid(UUID uuid);
}
