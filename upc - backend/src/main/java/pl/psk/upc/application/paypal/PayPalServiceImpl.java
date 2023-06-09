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
import pl.psk.upc.infrastructure.entity.OfferEntity;
import pl.psk.upc.infrastructure.entity.ProductEntity;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.contract.ContractDto;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.payment.CreatedPaymentDto;
import pl.psk.upc.web.payment.PaymentDto;
import pl.psk.upc.web.payment.PaymentInputDto;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.user.ClientDto;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
class PayPalServiceImpl implements PayPalService {

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

    public CreatedPaymentDto createPayment(PaymentInputDto inputDto) throws PayPalRESTException {
        MethodArgumentValidator.requiredNotNull(inputDto, "inputDto");
        double paymentAmount = 0.0;
        ContractDto contract = null;
        String serviceName = "";
        List<ProductDto> products = new ArrayList<>();

        if (inputDto.getOrderUuid() != null) {
            OrderDto order = orderService.getOrderByUuid(inputDto.getOrderUuid());
            contract = order.getService()
                    .getContract();
            paymentAmount = order.getAmount();
            serviceName = order.getService().getName();
            if(order.getProductDtos() != null) {
                products = order.getProductDtos();
            }
        } else {
            ServiceDto service = serviceService.getService(inputDto.getServiceUuid());
            contract = service.getContract();
            paymentAmount = contract.getAmount();
            serviceName = service.getName();
        }

        Payment payment = preparePayment(paymentAmount, inputDto);

        Payment approvedPayment = payment.create(apiContext);

        UUID paymentUuid = contractService.addNewPaymentToContract(contract.getUuid(), paymentAmount, serviceName, products, inputDto.getClientUuid());

        return CreatedPaymentDto.builder()
                .paymentUuid(paymentUuid)
                .link(getApprovalLink(approvedPayment))
                .build();
    }

    public CreatedPaymentDto createPayPalPaymentForExistingPayment(PaymentInputDto inputDto) throws PayPalRESTException {
        MethodArgumentValidator.requiredNotNull(inputDto, "inputDto");

        PaymentDto paymentDto = paymentService.findByUuid(inputDto.getPaymentUuid());
        Payment payment = preparePayment(roundAmount(paymentDto.getAmount()), inputDto);
        Payment approvedPayment = payment.create(apiContext);

        return CreatedPaymentDto.builder()
                .paymentUuid(paymentDto.getUuid())
                .link(getApprovalLink(approvedPayment))
                .build();
    }

    private Double roundAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String resultAsString = df.format(amount).replace(",", ".");
        return Double.parseDouble(resultAsString);
    }

    private RedirectUrls prepareRedirectUrls(PaymentInputDto inputDto) {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(inputDto.getCancelUrl());
        redirectUrls.setReturnUrl(inputDto.getSuccessUrl());
        return redirectUrls;
    }

    private Transaction prepareTransaction(double paymentAmount) {
        Amount amount = prepareAmount(paymentAmount);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        return transaction;
    }

    private Payer preparePayer(PaymentInputDto inputDto) {
        Payer payer = new Payer();
        PayerInfo payerInfo = new PayerInfo();
        ClientDto client = clientService.findByUuid(inputDto.getClientUuid());
        payerInfo.setFirstName(client.getFirstName());
        payerInfo.setLastName(client.getLastName());
        payerInfo.setEmail(client.getEmail());
        payer.setPayerInfo(payerInfo);
        payer.setPaymentMethod(PAYMENT_METHOD);
        return payer;
    }

    private Payment preparePayment(double paymentAmount, PaymentInputDto inputDto) {
        Transaction transaction = prepareTransaction(paymentAmount);
        Payer payer = preparePayer(inputDto);
        RedirectUrls redirectUrls = prepareRedirectUrls(inputDto);
        Payment payment = new Payment();
        payment.setIntent(INTENT);
        payment.setPayer(payer);
        payment.setTransactions(Collections.singletonList(transaction));
        payment.setRedirectUrls(redirectUrls);
        return payment;
    }

    private Amount prepareAmount(double paymentAmount) {
        Amount amount = new Amount();
        amount.setCurrency(CURRENCY);
        amount.setTotal(String.valueOf(paymentAmount));
        return amount;
    }

    private String getApprovalLink(Payment payment) {
        String approvalLink = "";
        for (Links l : payment.getLinks()) {
            if (l.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = l.getHref();
            }
        }
        return approvalLink;
    }

    public void executePayment(String paymentId, String payerId, UUID orderUuid, UUID paymentUuid, UUID clientUuid) throws PayPalRESTException {
        MethodArgumentValidator.requiredNotNullOrBlankString(paymentId, "paymentId");
        MethodArgumentValidator.requiredNotNullOrBlankString(payerId, "payerId");

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment();
        payment.setId(paymentId);

        Payment executedPayment = payment.execute(apiContext, paymentExecution);

        if (!executedPayment.getState().equals("approved")) {
            throw new PayPalRESTException("Payment not approved");
        }
        paymentService.updateStatus(paymentUuid, clientUuid);
        if (orderUuid != null) {
            orderService.updatePaymentStatusInOrder(orderUuid);
        }
    }

}
