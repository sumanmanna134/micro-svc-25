package com.example.catalog.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON response
public class AppResponse<T> extends ResponseEntity<AppResponse.Body<T>> {

    private final Meta meta;

    /**
     * Constructor for AppResponse using builder.
     *
     * @param data    the response body
     * @param meta    the meta information (e.g., count, limit)
     * @param headers the response headers
     * @param status  the HTTP status code
     */
    @Builder
    public AppResponse(
            T data, Meta meta, String message, MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(new Body<>(data, meta, message), headers, status == null ? HttpStatus.OK : status);
        this.meta = meta;
    }

    public static <T> AppResponse<List<T>> fromPage(Page<T> page, String message) {
        List<T> data = page.getContent();
        Meta metaData = Meta.builder()
                .count(page.getTotalElements())
                .perPage(page.getSize())
                .currentPage(page.getNumber() + 1)
                .pages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();
        return AppResponse.<List<T>>builder()
                .data(data)
                .meta(metaData)
                .message(message)
                .status(HttpStatus.OK)
                .build();
    }

    /**
     * Meta class for additional response metadata.
     */
    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Meta {
        private final long count;
        private final int perPage;
        private final int currentPage;
        private final int pages;
        private final boolean isFirst;
        private final boolean isLast;
        private final boolean hasNext;
        private final boolean hasPrevious;
    }

    /**
     * Wrapper class for response body and metadata.
     */
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Body<T> {
        private final T data;
        private final Meta meta;
        private final String message;
        private final LocalDateTime timestamp;

        public Body(T body, Meta meta, String message) {
            this.data = body;
            this.meta = meta;
            this.message = message == null ? "Operation executed" : message;
            this.timestamp = LocalDateTime.now();
        }
    }
}
