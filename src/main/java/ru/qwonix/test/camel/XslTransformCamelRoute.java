package ru.qwonix.test.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class XslTransformCamelRoute extends RouteBuilder {
    public static final String TRANSFORM_XML_URI = "transformXml";

    @Value("${path.to.xsl}")
    private String pathToXsl;

    @Override
    public void configure() {
        from("direct:" + TRANSFORM_XML_URI)
                .to("xslt:file:" + pathToXsl)
                .convertBodyTo(String.class);
    }
}