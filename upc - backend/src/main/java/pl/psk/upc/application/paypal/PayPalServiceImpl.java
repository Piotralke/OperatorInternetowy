package pl.psk.upc.application.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;
import pl.psk.upc.application.client.ClientService;
import pl.psk.upc.application.contract.ContractService;
import pl.psk.upc.application.order.OrderService;
import pl.psk.upc.application.payment.PaymentService;
import pl.psk.upc.application.service.ServiceService;
import pl.psk.upc.web.contract.ContractDto;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.payment.PaymentInputDto;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.user.ClientDto;

import java.util.Collections;
import java.util.UUID;

@Service
public class PayPalServiceImpl implements PayPalService {

    private final static String CURRENCY = "PLN";
    private final static String INTENT = "authorize";
    private final static String PAYMENT_METHOD = "paypal";

    private final APIContext apiContext;
    private final OrderService orderService;
    private final ClientService clientService;
    private final ContractService contractService;
    private final ServiceService serviceService;
    private final PaymentService paymentService;

    public PayPalServiceImpl(APIContext apiContext, OrderService orderService, ClientService clientService, ContractService contractService, ServiceService service, PaymentService paymentService) {
        this.apiContext = apiContext;
        this.orderService = orderService;
        this.clientService = clientService;
        this.contractService = contractService;
        this.serviceService = service;
        this.paymentService = paymentService;
    }

    public String createPayment(PaymentInputDto inputDto) throws PayPalRESTException {
        ContractDto byUuid = contractService.findByUuid(UUID.fromString("dca7c588-5397-4caa-a35f-7fa1e7cd3916"));
        double paymentAmount = 0.0;
        ContractDto contract = null;

        if (inputDto.getOrderUuid() != null) {
            OrderDto order = orderService.getOrderByUuid(inputDto.getOrderUuid());
            contract = order.getService()
                    .getContract();
            paymentAmount = order.getAmount();
        } else {
            ServiceDto service = serviceService.getService(inputDto.getServiceUuid());
            contract = service.getContract();
            paymentAmount = contract.getAmount();
        }

        Amount amount = new Amount();
        amount.setCurrency(CURRENCY);
        amount.setTotal(String.valueOf(paymentAmount));

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

        ContractDto contractDto = contractService.addNewPaymentToContract(contract.getUuid(), paymentAmount);
        return approvalLink;
    }

    public OrderDto executePayment(String paymentId, String payerId, UUID orderUuid, UUID paymentUuid) throws PayPalRESTException {
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
        if (orderUuid == null) {
            paymentService.updateStatus(paymentUuid);
            return orderService.getOrderByUuid(orderUuid);
        } else {
            return orderService.updateOrderStatus(orderUuid);
        }
    }

}
