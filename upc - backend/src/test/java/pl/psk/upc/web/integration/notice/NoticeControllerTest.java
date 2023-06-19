package pl.psk.upc.web.integration.notice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.application.notice.NoticeService;
import pl.psk.upc.web.UpcTest;
import pl.psk.upc.web.notice.NoticeDto;
import pl.psk.upc.web.notice.NoticeDtoWrapper;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NoticeControllerTest extends UpcTest {

    @Autowired
    NoticeService noticeService;
    @Test @Transactional
    void saveNotice() {
        NoticeDtoWrapper allByUserBeforeSave = noticeService.getAllByUser(clientAccount.getUuid());
        List<UUID> uuids = noticeService.saveNotice(saveNoticeRequestDto);
        NoticeDtoWrapper allByUserAfterSave = noticeService.getAllByUser(clientAccount.getUuid());
        NoticeDto savedNotice = noticeService.getByUuid(uuids.get(0));

        assertEquals(allByUserBeforeSave.getContent().size(), 0);
        assertEquals(allByUserAfterSave.getContent().size(), 1);
        assertEquals(uuids.size(), 1);
        assertEquals(savedNotice.getDescription(), saveNoticeRequestDto.getDescription());
        assertEquals(savedNotice.getClient().getUuid(), saveNoticeRequestDto.getClientsUuid().get(0));
        Assertions.assertFalse(savedNotice.isClicked());
        Assertions.assertNotNull(savedNotice.getNoticeDate());
        Assertions.assertNotNull(savedNotice.getUuid());
        assertEquals(savedNotice.getDescription(), saveNoticeRequestDto.getDescription());
        assertEquals(savedNotice.getDescription(), saveNoticeRequestDto.getDescription());
    }

    @Test @Transactional
    void getAllByUser() {
        NoticeDtoWrapper allByUserBeforeSave = noticeService.getAllByUser(clientAccount.getUuid());
        noticeService.saveNotice(saveNoticeRequestDto);
        noticeService.saveNotice(saveNoticeRequestDto);
        NoticeDtoWrapper allByUserAfterSave = noticeService.getAllByUser(clientAccount.getUuid());

        assertEquals(0, allByUserBeforeSave.getContent().size());
        assertEquals(2, allByUserAfterSave.getContent().size());
    }

    @Test @Transactional
    void getByUuid() {
        List<UUID> uuids = noticeService.saveNotice(saveNoticeRequestDto);
        NoticeDto savedNotice = noticeService.getByUuid(uuids.get(0));

        assertEquals(savedNotice.getDescription(), saveNoticeRequestDto.getDescription());
        assertEquals(savedNotice.getClient().getUuid(), saveNoticeRequestDto.getClientsUuid().get(0));
        Assertions.assertFalse(savedNotice.isClicked());
        Assertions.assertNotNull(savedNotice.getNoticeDate());
        Assertions.assertNotNull(savedNotice.getUuid());
        assertEquals(savedNotice.getDescription(), saveNoticeRequestDto.getDescription());
        assertEquals(savedNotice.getDescription(), saveNoticeRequestDto.getDescription());
    }

    @Test @Transactional
    void editNoticeStatus() {
        List<UUID> uuids = noticeService.saveNotice(saveNoticeRequestDto);
        NoticeDto noticeBeforeEdit = noticeService.getByUuid(uuids.get(0));
        Assertions.assertFalse(noticeBeforeEdit.isClicked());
        noticeService.editNoticeStatus(uuids.get(0), true);
        NoticeDto noticeAfterEdit = noticeService.getByUuid(uuids.get(0));
        Assertions.assertTrue(noticeAfterEdit.isClicked());
    }
}