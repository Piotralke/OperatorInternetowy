package pl.psk.upc.web.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.web.user.ClientDto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Value
public class NoticeDto {

    private final static String UUID = "uuid";
    private final static String NOTICE_DATE = "noticeDate";
    private final static String DESCRIPTION = "description";
    private final static String IS_CLICKED = "isClicked";
    private final static String CLIENT = "client";

    @JsonProperty(UUID)
    UUID uuid;

    @JsonProperty(NOTICE_DATE)
    ZonedDateTime noticeDate;

    @JsonProperty(DESCRIPTION)
    String description;

    @JsonProperty(IS_CLICKED)
    boolean isClicked;

    @JsonProperty(CLIENT)
    ClientDto client;

    @Builder
    public NoticeDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(NOTICE_DATE) ZonedDateTime noticeDate, @JsonProperty(DESCRIPTION) String description, @JsonProperty(IS_CLICKED) boolean isClicked,
                     @JsonProperty(CLIENT) ClientDto client) {
        this.uuid = uuid;
        this.noticeDate = noticeDate;
        this.description = description;
        this.isClicked = isClicked;
        this.client = client;
    }

}
