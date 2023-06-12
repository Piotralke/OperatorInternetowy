package pl.psk.upc.web.contract;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.application.contract.ContractService;
import pl.psk.upc.infrastructure.enums.ContractLengthEnum;
import pl.psk.upc.web.UpcRestPaths;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping(UpcRestPaths.GET_CONTRACT_LENGTHS)
    public List<ContractLengthEnum> getContractLengths() {
        return Arrays.stream(ContractLengthEnum.values())
                .toList();
    }

    @GetMapping(UpcRestPaths.GET_CONTRACT_BY_UUID)
    public ContractDto getContractByUuid(@PathVariable(value = "uuid") UUID uuid) {
        return contractService.findByUuid(uuid);
    }

    @GetMapping(UpcRestPaths.GET_CONTRACTS_BY_USER)
    public ContractDtoWrapper getContractByUserUuid(@PathVariable(value = "uuid") UUID uuid) {
        return contractService.findByUserUuid(uuid);
    }

    @GetMapping(UpcRestPaths.GET_CONTRACTS_BY_SERVICE)
    public ContractDto getContractByServiceUuid(@PathVariable(value = "uuid") UUID uuid) {
        return contractService.findByServiceUuid(uuid);
    }
}
