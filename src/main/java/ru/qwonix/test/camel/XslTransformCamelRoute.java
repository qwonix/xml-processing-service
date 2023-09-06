package ru.qwonix.test.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class XslTransformCamelRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:transformXml")
                .to("xslt:file:{{path.to.xsl}}")
                .convertBodyTo(String.class);
    }
}