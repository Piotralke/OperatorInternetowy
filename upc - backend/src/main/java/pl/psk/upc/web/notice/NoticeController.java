package pl.psk.upc.web.notice;

import org.springframework.web.bind.annotation.*;
import pl.psk.upc.application.notice.NoticeService;
import pl.psk.upc.web.UpcRestPaths;

import java.util.List;
import java.util.UUID;

@RestController
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping(UpcRestPaths.SAVE_NOTICE)
    public List<UUID> saveNotice(@RequestBody SaveNoticeRequestDto saveNoticeRequestDto) {
        return noticeService.saveNotice(saveNoticeRequestDto);
    }

    @GetMapping(UpcRestPaths.GET_ALL_NOTICES_BY_USER)
    public NoticeDtoWrapper getAllByUser(@PathVariable(value = "uuid") UUID userUuid ){
        return noticeService.getAllByUser(userUuid);
    }

    @GetMapping(UpcRestPaths.GET_NOTICE_BY_UUID)
    public NoticeDto getByUuid(@PathVariable(value = "uuid") UUID uuid) {
        return noticeService.getByUuid(uuid);
    }


    @PutMapping(UpcRestPaths.EDIT_NOTICE_STATUS)
    public UUID editNoticeStatus(@PathVariable(value = "uuid") UUID uuid, @RequestParam boolean isClicked) {
        return noticeService.editNoticeStatus(uuid, isClicked);
    }

}
