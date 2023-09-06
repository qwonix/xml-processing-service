package ru.qwonix.test.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class StoreCamelRoute extends RouteBuilder {

    public static final String SAVE_TO_DATABASE_URI = "saveToDatabase";

    @Override
    public void configure() {
        from("direct:" + SAVE_TO_DATABASE_URI)
                .bean("documentTransformationHistoryService", "save")
                .log("New DocumentTransformationHistory successfully saved");
    }
}