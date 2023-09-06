package ru.qwonix.test.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class StoreCamelRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:saveToDatabase")
                .bean("documentTransformationHistoryService", "save")
                .log("New DocumentTransformationHistory successfully saved");
    }
}