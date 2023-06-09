package pl.psk.upc.application.payment;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;
import pl.psk.upc.application.client.ClientService;
import pl.psk.upc.application.order.OrderService;
import pl.psk.upc.infrastructure.entity.ContractEntity;
import pl.psk.upc.infrastructure.entity.PaymentEntity;
import pl.psk.upc.infrastructure.entity.PaymentStatus;
import pl.psk.upc.infrastructure.repository.ContractRepository;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.payment.PaymentInputDto;
import pl.psk.upc.web.user.ClientDto;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class PayPalServiceImpl implements PaymentService {

    private final static String CURRENCY = "PLN";
    private final static String INTENT = "authorize";
    private final static String PAYMENT_METHOD = "paypal";

    private final APIContext apiContext;
    private final OrderService orderService;
    private final ClientService clientService;
    private final ContractRepository contractRepository;


    public PayPalServiceImpl(APIContext apiContext, OrderService orderService, ClientService clientService, ContractRepository contractRepository) {
        this.apiContext = apiContext;
        this.orderService = orderService;
        this.clientService = clientService;
        this.contractRepository = contractRepository;
    }

    public String createPayment(PaymentInputDto inputDto) throws PayPalRESTException {
        OrderDto order = orderService.getOrderByUuid(inputDto.getOrderUuid());

        Amount amount = new Amount();
        amount.setCurrency(CURRENCY);
        amount.setTotal(order.getAmount().toString());

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(inputDto.getCancelUrl());
        redirectUrls.setReturnUrl(inputDto.getSuccessUrl());

        Payer payer = new Payer();
        PayerInfo payerInfo = new PayerInfo();
        ClientDto client = clientService.findByUuid(inputDto.getClientUuid());
        payerInfo.setFirstName(client.getFirstName());
        payerInfo.setLastName(client.getLastName());
        payerInfo.setEmail(client.getEmail());
        payer.setPayerInfo(payerInfo);
        payer.setPaymentMethod(PAYMENT_METHOD);

        Payment payment = new Payment();
        payment.setIntent(INTENT);
        payment.setPayer(payer);
        payment.setTransactions(Collections.singletonList(transaction));
        payment.setRedirectUrls(redirectUrls);
        Payment approvedPayment = payment.create(apiContext);

        String approvalLink = "";
        for (Links l : approvedPayment.getLinks()) {
            if (l.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = l.getHref();
            }
        }

        ContractEntity contract = order.getService().getContract();
        PaymentEntity userPayment = PaymentEntity.builder()
                .isPaymentCompleted(false)
                .uuid(UUID.randomUUID())
                .amount(order.getAmount())
                .date(ZonedDateTime.now(ZoneId.systemDefault()))
                .build();
        List<PaymentEntity> payments = contract.getPaymentEntity();
        if (payments == null) {
            payments = new ArrayList<>();
        }
        payments.add(userPayment);
        contract.setPaymentEntity(payments);
        contractRepository.save(contract);

        return approvalLink;
    }

    public OrderDto executePayment(String paymentId, String payerId, UUID orderUuid) throws PayPalRESTException {
//        PaymentExecution paymentExecution = new PaymentExecution();
//        paymentExecution.setPayerId(payerId);
//
//        Payment payment = new Payment();
//        payment.setId(paymentId);
//
//        Payment executedPayment = payment.execute(apiContext, paymentExecution);
//
//        if (!executedPayment.getState().equals("approved")) {
//            throw new PayPalRESTException("Payment not approved");
//        }

        return orderService.updateOrderStatus(PaymentStatus.OPLACONE, orderUuid);
    }

}
