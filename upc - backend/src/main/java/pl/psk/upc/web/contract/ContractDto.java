package pl.psk.upc.web.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.web.offer.OfferDto;
import pl.psk.upc.web.payment.PaymentDto;

import java.time.ZonedDateTime;
import java.util.List;

@Value
public class ContractDto {

    private final static String UUID = "uuid";
    private final static String START_DATE = "startDate";
    private final static String END_DATE = "endDate";
    private final static String AMOUNT = "amount";
    private final static String OFFER = "offer";
    private final static String PAYMENTS = "payments";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(START_DATE)
    ZonedDateTime startDate;

    @JsonProperty(END_DATE)
    ZonedDateTime endDate;

    @JsonProperty(AMOUNT)
    Double amount;

    @JsonProperty(OFFER)
    OfferDto offer;

    @JsonProperty(PAYMENTS)
    List<PaymentDto> payments;

    @Builder
    public ContractDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(START_DATE) ZonedDateTime startDate, @JsonProperty(END_DATE) ZonedDateTime endDate, @JsonProperty(AMOUNT) Double amount,
                       @JsonProperty(OFFER) OfferDto offer, @JsonProperty(PAYMENTS) List<PaymentDto> payments) {
        this.uuid = uuid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.offer = offer;
        this.payments = payments;
    }
}
