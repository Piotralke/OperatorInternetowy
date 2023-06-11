package pl.psk.upc.application.payment;

import pl.psk.upc.application.product.ProductConverter;
import pl.psk.upc.infrastructure.entity.PaymentEntity;
import pl.psk.upc.infrastructure.entity.ProductEntity;
import pl.psk.upc.web.payment.PaymentDto;
import pl.psk.upc.web.payment.PaymentDtoWrapper;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.product.ProductDtoWrapper;

import java.util.List;

public class PaymentConverter {

    public static PaymentDto convertFrom(PaymentEntity payment) {
        if (payment == null) {
            return PaymentDto.builder().build();
        }

        return PaymentDto.builder()
                .uuid(payment.getUuid())
                .date(payment.getDate())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .name(payment.getName())
                .productDtosUuids(payment.getProductsUuid())
                .serviceUuid(payment.getServiceUuid())
                .build();
    }

    public static PaymentDtoWrapper convertFrom(List<PaymentEntity> payments) {
        List<PaymentDto> convertedPayments = payments.stream()
                .map(PaymentConverter::convertFrom)
                .toList();

        return PaymentDtoWrapper.builder()
                .content(convertedPayments)
                .build();
    }

}
