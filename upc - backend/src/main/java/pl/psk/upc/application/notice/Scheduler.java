package pl.psk.upc.application.notice;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.psk.upc.application.payment.PaymentService;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.NoticeEntity;
import pl.psk.upc.infrastructure.entity.PaymentEntity;
import pl.psk.upc.infrastructure.entity.ServiceEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@EnableScheduling
public class Scheduler {
    private final static String PREFIX_PAYMENT_NOTICE_REMAINDER_MESSAGE = "Payment term for the service ";
    private final static String POSTFIX_PAYMENT_NOTICE_REMAINDER_MESSAGE = " expires in 2 days";

    private final PaymentService paymentService;
    private final NoticeService noticeService;

    public Scheduler(PaymentService paymentService, NoticeService noticeService) {
        this.paymentService = paymentService;
        this.noticeService = noticeService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void createPaymentReminder() {
        List<PaymentEntity> allNotPayedPayments = paymentService.getAllNotPayedPayments();
        List<PaymentEntity> result = allNotPayedPayments.stream()
                .filter(paymentDto -> ZonedDateTime.now(ZoneId.systemDefault()).toLocalDate().isEqual(paymentDto.getDate().plusDays(12L).toLocalDate()))
                .toList();

        saveNotices(result);
    }

    private void saveNotices(List<PaymentEntity> payments) {
        List<NoticeEntity> notices = new ArrayList<>();
        for (PaymentEntity p : payments) {
            notices.add(createNotice(p));
        }
        noticeService.saveNoticesFromScheduler(notices);
    }

    private NoticeEntity createNotice(PaymentEntity payment) {
        ServiceEntity service = payment.getContractEntity()
                .getServiceEntity();
        ClientAccountEntity clientAccountEntity = service.getClientAccountEntity();
        return NoticeEntity.builder()
                .uuid(UUID.randomUUID())
                .description(prepareMessage(service.getName()))
                .noticeDate(ZonedDateTime.now(ZoneId.systemDefault()))
                .isClicked(false)
                .clientAccountEntity(clientAccountEntity)
                .build();
    }

    private String prepareMessage(String serviceName) {
        return PREFIX_PAYMENT_NOTICE_REMAINDER_MESSAGE + serviceName + POSTFIX_PAYMENT_NOTICE_REMAINDER_MESSAGE;
    }
}
