package ru.qwonix.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.qwonix.test.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
