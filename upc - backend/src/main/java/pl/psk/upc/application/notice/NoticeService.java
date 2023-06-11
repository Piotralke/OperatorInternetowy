package pl.psk.upc.application.notice;

import pl.psk.upc.web.notice.NoticeDto;
import pl.psk.upc.web.notice.NoticeDtoWrapper;
import pl.psk.upc.web.notice.SaveNoticeRequestDto;

import java.util.List;
import java.util.UUID;

public interface NoticeService {
    List<UUID> saveNotice(SaveNoticeRequestDto saveNoticeRequestDto);
    NoticeDtoWrapper getAllByUser(UUID uuid);
    NoticeDto getByUuid(UUID uuid);
    UUID editNoticeStatus(UUID uuid, boolean isClicked);
}
