package ru.qwonix.test.service;

import ru.qwonix.test.dto.DocumentResponse;
import ru.qwonix.test.dto.ShortDocumentTransformationHistoryResponse;
import ru.qwonix.test.entity.DocumentTransformationHistory;

import java.util.List;

public interface DocumentTransformationHistoryService {
    List<ShortDocumentTransformationHistoryResponse> findAllShort();

    DocumentResponse findById(Long id);

    DocumentTransformationHistory save(DocumentTransformationHistory documentTransformationHistory);
}
