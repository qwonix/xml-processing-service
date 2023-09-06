package ru.qwonix.test.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import ru.qwonix.test.config.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@Data
@Builder
public class ShortDocumentTransformationHistoryResponse {
    private final Long id;
    private final String originalFileName;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime receivedAt;
}
