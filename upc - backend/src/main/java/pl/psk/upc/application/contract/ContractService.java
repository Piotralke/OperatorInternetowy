package pl.psk.upc.application.contract;

import pl.psk.upc.web.contract.ContractDto;
import pl.psk.upc.web.contract.ContractDtoWrapper;
import pl.psk.upc.web.product.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ContractService {
    UUID addNewPaymentToContract(UUID contractUuid, double paymentAmount, String serviceName, List<ProductDto> products, UUID clientUuid);
    ContractDto findByUuid(UUID uuid);
    ContractDtoWrapper findByUserUuid(UUID uuid);
    ContractDto findByServiceUuid(UUID uuid);
}
