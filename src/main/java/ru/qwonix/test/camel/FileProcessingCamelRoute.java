package ru.qwonix.test.camel;

import lombok.RequiredArgsConstructor;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.qwonix.test.entity.DocumentTransformationHistory;

import java.time.Duration;

import static ru.qwonix.test.camel.StoreCamelRoute.SAVE_TO_DATABASE_URI;
import static ru.qwonix.test.camel.XslTransformCamelRoute.TRANSFORM_XML_URI;

@RequiredArgsConstructor
@Component
public class FileProcessingCamelRoute extends RouteBuilder {

    private final AggregationStrategy documentHistoryAggregationStrategy;

    @Value("${directory.check.rate}")
    public Duration directoryCheckRate;

    @Value("${path.to.directory.z}")
    public String pathToDirectoryZ;

    @Override
    public void configure() {
        from("file:" + pathToDirectoryZ + "?" +
             "delete=true&" +
             "delay=" + directoryCheckRate.toMillis() +
             "&include=.*.xml")
                .log("New file detected: ${header.CamelFileName}")
                .enrich("direct:" + TRANSFORM_XML_URI, documentHistoryAggregationStrategy)
                .convertBodyTo(DocumentTransformationHistory.class)
                .to("direct:" + SAVE_TO_DATABASE_URI);
    }
}