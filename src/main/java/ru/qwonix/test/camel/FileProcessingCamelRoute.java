package ru.qwonix.test.camel;

import lombok.RequiredArgsConstructor;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.qwonix.test.entity.DocumentTransformationHistory;

import static ru.qwonix.test.camel.StoreCamelRoute.SAVE_TO_DATABASE_URI;
import static ru.qwonix.test.camel.XslTransformCamelRoute.TRANSFORM_XML_URI;

@RequiredArgsConstructor
@Component
public class FileProcessingCamelRoute extends RouteBuilder {

    public static final String DIRECTORY_CHECK_RATE = "directory.check.rate";
    public static final String PATH_TO_DIRECTORY_Z = "path.to.directory.z";


    private final AggregationStrategy documentHistoryAggregationStrategy;

    @Override
    public void configure() {
        from("file:{{" + PATH_TO_DIRECTORY_Z + "}}?" +
                                                             "delete=true&" +
                                                             "delay={{" + DIRECTORY_CHECK_RATE + "}}" +
                                                             "&include=.*.xml")
                .log("New file detected: ${header.CamelFileName}")
                .enrich("direct:" + TRANSFORM_XML_URI, documentHistoryAggregationStrategy)
                .convertBodyTo(DocumentTransformationHistory.class)
                .to("direct:" + SAVE_TO_DATABASE_URI);
    }
}