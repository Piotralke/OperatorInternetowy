package pl.psk.upc.application.contract;

import pl.psk.upc.web.contract.ContractDto;
import pl.psk.upc.web.contract.ContractDtoWrapper;

import java.util.UUID;

public interface ContractService {
    ContractDto addNewPaymentToContract(UUID contractUuid, double paymentAmount);
    ContractDto findByUuid(UUID uuid);
    ContractDtoWrapper findByUserUuid(UUID uuid);
    ContractDto findByServiceUuid(UUID uuid);
}
