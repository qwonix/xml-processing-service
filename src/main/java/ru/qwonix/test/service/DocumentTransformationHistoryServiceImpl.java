package ru.qwonix.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.qwonix.test.DocumentMapper;
import ru.qwonix.test.dto.DocumentResponse;
import ru.qwonix.test.dto.ShortDocumentTransformationHistoryResponse;
import ru.qwonix.test.entity.DocumentTransformationHistory;
import ru.qwonix.test.repository.DocumentTransformationHistoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DocumentTransformationHistoryServiceImpl implements DocumentTransformationHistoryService {
    private final DocumentTransformationHistoryRepository documentTransformationHistoryRepository;
    private final DocumentMapper documentMapper;

    @Override
    public List<ShortDocumentTransformationHistoryResponse> findAllShort() {
        return documentTransformationHistoryRepository.findAll().stream()
                .map(documentMapper::mapToShort).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public DocumentResponse findById(Long id) {
        DocumentTransformationHistory documentTransformationHistory = documentTransformationHistoryRepository.findOne(id);
        if (documentTransformationHistory == null) {
            return null;
        }
        return documentMapper.map(documentTransformationHistory);
    }

    @Override
    public DocumentTransformationHistory save(DocumentTransformationHistory documentTransformationHistory) {
        return documentTransformationHistoryRepository.save(documentTransformationHistory);
    }
}
