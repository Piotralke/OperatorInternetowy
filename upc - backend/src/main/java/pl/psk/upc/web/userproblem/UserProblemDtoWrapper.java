package pl.psk.upc.web.userproblem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class UserProblemDtoWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<UserProblemDto> content;

    @Builder
    public UserProblemDtoWrapper(@JsonProperty(CONTENT) List<UserProblemDto> content) {
        this.content = content;
    }

    public List<UserProblemDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }

}
