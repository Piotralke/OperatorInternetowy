package pl.psk.upc.web.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class EmployeeWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<EmployeeDto> content;

    @Builder
    public EmployeeWrapper(@JsonProperty(CONTENT) List<EmployeeDto> content) {
        this.content = content;
    }

    public List<EmployeeDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }

}
