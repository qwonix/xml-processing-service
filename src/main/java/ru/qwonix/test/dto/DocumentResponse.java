package ru.qwonix.test.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.qwonix.test.config.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Objects;


@Setter
@Getter

@Builder
public class DocumentResponse {
    private Long id;
    private String originalFileName;
    private String original;
    private String transformed;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime receivedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentResponse that = (DocumentResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(originalFileName, that.originalFileName) && Objects.equals(original, that.original) && Objects.equals(transformed, that.transformed) && Objects.equals(receivedAt, that.receivedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalFileName, original, transformed, receivedAt);
    }
}
