package ru.qwonix.test;


import org.springframework.stereotype.Component;
import ru.qwonix.test.dto.DocumentResponse;
import ru.qwonix.test.dto.ShortDocumentTransformationHistoryResponse;
import ru.qwonix.test.entity.DocumentTransformationHistory;

@Component
public class DocumentMapper {

    public ShortDocumentTransformationHistoryResponse mapToShort(DocumentTransformationHistory documentTransformationHistory) {
        return ShortDocumentTransformationHistoryResponse.builder()
                .id(documentTransformationHistory.getId())
                .originalFileName(documentTransformationHistory.getOriginalFileName())
                .receivedAt(documentTransformationHistory.getReceivedAt())
                .build();
    }

    public DocumentResponse map(DocumentTransformationHistory documentTransformationHistory) {
        return DocumentResponse.builder()
                .id(documentTransformationHistory.getId())
                .originalFileName(documentTransformationHistory.getOriginalFileName())
                .original(documentTransformationHistory.getOriginal().getData())
                .transformed(documentTransformationHistory.getTransformed().getData())
                .receivedAt(documentTransformationHistory.getReceivedAt())
                .build();
    }
}
