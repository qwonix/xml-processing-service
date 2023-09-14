package ru.qwonix.test.camel;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import ru.qwonix.test.entity.Document;
import ru.qwonix.test.entity.DocumentTransformationHistory;

import java.time.LocalDateTime;

@Component
public class DocumentHistoryAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Document original = new Document(oldExchange.getIn().getBody(String.class));
        Document transformed = new Document(newExchange.getIn().getBody(String.class));
        String fileName = oldExchange.getIn().getHeader("CamelFileName", String.class);

        DocumentTransformationHistory transformationHistory = DocumentTransformationHistory.builder()
                .originalFileName(fileName)
                .original(original)
                .transformed(transformed)
                .receivedAt(LocalDateTime.now())
                .build();

        oldExchange.getIn().setBody(transformationHistory);
        return oldExchange;
    }
}
