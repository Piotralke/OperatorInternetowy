package pl.psk.upc.tech;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
//@Immutable
//@ThreadSafe
@SuperBuilder(toBuilder = true)
@ToString
public class PageDetails {

    @JsonProperty("page")
    Integer page;
    @JsonProperty("size")
    Integer size;
    @JsonProperty("totalElements")
    Long totalElements;
    @JsonProperty("totalPages")
    Integer totalPages;

    @JsonCreator
    public PageDetails(@JsonProperty("page") Integer page,
                       @JsonProperty("size") Integer size,
                       @JsonProperty("totalElements") Long totalElements,
                       @JsonProperty("totalPages") Integer totalPages) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
