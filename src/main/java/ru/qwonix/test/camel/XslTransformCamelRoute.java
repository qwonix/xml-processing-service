package ru.qwonix.test.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class XslTransformCamelRoute extends RouteBuilder {
    public static final String TRANSFORM_XML_URI = "transformXml";

    @Override
    public void configure() {
        from("direct:" + TRANSFORM_XML_URI)
                .to("xslt:file:{{path.to.xsl}}")
                .convertBodyTo(String.class);
    }
}