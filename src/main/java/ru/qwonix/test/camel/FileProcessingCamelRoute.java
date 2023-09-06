package ru.qwonix.test.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.qwonix.test.entity.Document;
import ru.qwonix.test.entity.DocumentTransformationHistory;

import java.time.LocalDateTime;

@Component
public class FileProcessingCamelRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("file:{{path.to.directory.z}}?delete=true&delay={{directory.check.rate}}")
                .log("New file detected: ${header.CamelFileName}")
                .enrich("direct:transformXml", (oldExchange, newExchange) -> {
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
                .to("direct:saveToDatabase");
    }
}