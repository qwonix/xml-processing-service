package ru.qwonix.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.qwonix.test.entity.DocumentTransformationHistory;

public interface DocumentTransformationHistoryRepository extends JpaRepository<DocumentTransformationHistory, Long> {
}
