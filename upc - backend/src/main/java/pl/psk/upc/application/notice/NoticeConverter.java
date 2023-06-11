package pl.psk.upc.application.notice;

import pl.psk.upc.application.client.ClientConverter;
import pl.psk.upc.infrastructure.entity.NoticeEntity;
import pl.psk.upc.web.notice.NoticeDto;
import pl.psk.upc.web.notice.NoticeDtoWrapper;

import java.util.List;

public class NoticeConverter {

    public static NoticeDto convertFrom(NoticeEntity noticeEntity) {

        return NoticeDto.builder()
                .uuid(noticeEntity.getUuid())
                .noticeDate(noticeEntity.getNoticeDate())
                .description(noticeEntity.getDescription())
                .isClicked(noticeEntity.isClicked())
                .client(ClientConverter.convertFrom(noticeEntity.getClientAccountEntity()))
                .build();
    }

    public static NoticeDtoWrapper convertFrom(List<NoticeEntity> noticeEntities) {
        List<NoticeDto> convertedNotices = noticeEntities.stream()
                .map(NoticeConverter::convertFrom)
                .toList();

        return NoticeDtoWrapper.builder()
                .content(convertedNotices)
                .build();
    }

}
