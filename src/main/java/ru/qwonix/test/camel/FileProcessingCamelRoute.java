package ru.qwonix.test.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.qwonix.test.entity.Document;
import ru.qwonix.test.entity.DocumentTransformationHistory;

import java.time.LocalDateTime;

import static ru.qwonix.test.camel.StoreCamelRoute.SAVE_TO_DATABASE_URI;
import static ru.qwonix.test.camel.XslTransformCamelRoute.TRANSFORM_XML_URI;

@Component
public class FileProcessingCamelRoute extends RouteBuilder {


    @Override
    public void configure() {
        from("file:{{path.to.directory.z}}?delete=true&delay={{directory.check.rate}}&include=.*.xml")
                .log("New file detected: ${header.CamelFileName}")
                .enrich("direct:" + TRANSFORM_XML_URI, (oldExchange, newExchange) -> {
                    Document original = new Document(oldExchange.getIn().getBody(String.class));
                    Document transformed = new Document(newExchange.getIn().getBody(String.class));
                    DocumentTransformationHistory transformationHistory = DocumentTransformationHistory.builder()
                            .originalFileName(oldExchange.getIn().getHeader("CamelFileName", String.class))
                            .original(original)
                            .transformed(transformed)
                            .receivedAt(LocalDateTime.now())
                            .build();

                    oldExchange.getIn().setBody(transformationHistory);
                    return oldExchange;
                })
                .convertBodyTo(DocumentTransformationHistory.class)
                .to("direct:" + SAVE_TO_DATABASE_URI);
    }
}