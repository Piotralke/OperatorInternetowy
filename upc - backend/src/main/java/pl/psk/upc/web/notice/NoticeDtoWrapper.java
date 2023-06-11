package pl.psk.upc.web.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class NoticeDtoWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<NoticeDto> content;

    @Builder
    public NoticeDtoWrapper(@JsonProperty(CONTENT) List<NoticeDto> content) {
        this.content = content;
    }

    public List<NoticeDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }

}
