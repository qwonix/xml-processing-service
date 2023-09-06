package ru.qwonix.test.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import ru.qwonix.test.config.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@Data
@Builder
public class DocumentResponse {
    private Long id;
    private String originalFileName;
    private String original;
    private String transformed;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime receivedAt;
}
