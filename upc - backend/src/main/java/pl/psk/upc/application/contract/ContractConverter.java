package pl.psk.upc.application.contract;

import pl.psk.upc.application.offer.OfferConverter;
import pl.psk.upc.application.payment.PaymentConverter;
import pl.psk.upc.infrastructure.entity.ContractEntity;
import pl.psk.upc.web.contract.ContractDto;
import pl.psk.upc.web.contract.ContractDtoWrapper;

import java.util.List;

public class ContractConverter {

    public static ContractDto convertFrom(ContractEntity contract) {
        if (contract == null) {
            return ContractDto.builder().build();
        }

        return ContractDto.builder()
                .uuid(contract.getUuid())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .amount(contract.getAmount())
                .offer(OfferConverter.convertFrom(contract.getOfferEntity()))
                .payments(PaymentConverter.convertFrom(contract.getPaymentEntities()).getContent())
                .build();
    }

    public static ContractDtoWrapper convertFrom(List<ContractEntity> contracts) {
        List<ContractDto> convertedProducts = contracts.stream()
                .map(ContractConverter::convertFrom)
                .toList();

        return ContractDtoWrapper.builder()
                .content(convertedProducts)
                .build();
    }

}
