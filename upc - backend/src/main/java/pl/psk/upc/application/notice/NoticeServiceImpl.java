package pl.psk.upc.application.notice;

import org.springframework.stereotype.Service;
import pl.psk.upc.exception.GenericNotFoundException;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.NoticeEntity;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.NoticeRepository;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.notice.NoticeDto;
import pl.psk.upc.web.notice.NoticeDtoWrapper;
import pl.psk.upc.web.notice.SaveNoticeRequestDto;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final ClientRepository clientRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository, ClientRepository clientRepository) {
        this.noticeRepository = noticeRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<UUID> saveNotice(SaveNoticeRequestDto saveNoticeRequestDto) {
        MethodArgumentValidator.requiredNotNull(saveNoticeRequestDto, "saveNoticeRequestDto");
        List<ClientAccountEntity> clients = saveNoticeRequestDto.getClientsUuid().stream()
                .map(clientRepository::findByUuid)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        List<NoticeEntity> result = new ArrayList<>();
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.systemDefault());
        for (ClientAccountEntity client : clients) {
            result.add(NoticeEntity.builder()
                    .uuid(UUID.randomUUID())
                    .noticeDate(dateTime)
                    .description(saveNoticeRequestDto.getDescription())
                    .isClicked(false)
                    .clientAccountEntity(client)
                    .build());
        }

        return noticeRepository.saveAll(result).stream()
                .map(NoticeEntity::getUuid)
                .toList();
    }

    @Override
    public NoticeDtoWrapper getAllByUser(UUID clientUuid) {
        MethodArgumentValidator.requiredNotNull(clientUuid, "clientUuid");
        List<NoticeEntity> allByClientAccountEntityUuid = noticeRepository.findAllByClientAccountEntity_Uuid(clientUuid);
        return NoticeConverter.convertFrom(allByClientAccountEntityUuid);
    }

    @Override
    public NoticeDto getByUuid(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        NoticeEntity notice = noticeRepository.findByUuid(uuid)
                .orElseThrow(() -> new GenericNotFoundException("Notice not found"));
        return NoticeConverter.convertFrom(notice);
    }

    @Override
    public UUID editNoticeStatus(UUID uuid, boolean isClicked) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        NoticeEntity notice = noticeRepository.findByUuid(uuid)
                .orElseThrow(() -> new GenericNotFoundException("Notice not found"));
        notice.setClicked(isClicked);
        return noticeRepository.save(notice)
                .getUuid();
    }
}
