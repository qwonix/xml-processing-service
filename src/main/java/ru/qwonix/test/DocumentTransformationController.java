package ru.qwonix.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.qwonix.test.dto.DocumentResponse;
import ru.qwonix.test.dto.ShortDocumentTransformationHistoryResponse;

import java.util.List;

@RestController
@RequestMapping("api/document")
public class DocumentTransformationController {

    private final DocumentTransformationHistoryService documentTransformationHistoryService;

    public DocumentTransformationController(DocumentTransformationHistoryService documentTransformationHistoryService) {
        this.documentTransformationHistoryService = documentTransformationHistoryService;
    }

    // REST сервис, для получения списка полученных документов
    @GetMapping
    public ResponseEntity<List<ShortDocumentTransformationHistoryResponse>> all() {
        return ResponseEntity.ok(documentTransformationHistoryService.findAllShort());
    }

    // REST сервис, для скачивания документа xml по конкретному id
    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> one(@PathVariable("id") Long id) {
        DocumentResponse document = documentTransformationHistoryService.findById(id);
        if (document == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(document);
    }

    @GetMapping(value = "/{id}/original", produces = "application/xml;charset=UTF-8")
    public ResponseEntity<String> original(@PathVariable("id") Long id) {
        DocumentResponse document = documentTransformationHistoryService.findById(id);
        if (document == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(document.getOriginal());
    }

    @GetMapping(value = "/{id}/transformed", produces = "application/xml;charset=UTF-8")
    public ResponseEntity<String> transformed(@PathVariable("id") Long id) {
        DocumentResponse document = documentTransformationHistoryService.findById(id);
        if (document == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(document.getTransformed());
    }
}
