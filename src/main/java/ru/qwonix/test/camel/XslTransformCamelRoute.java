package ru.qwonix.test.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class XslTransformCamelRoute extends RouteBuilder {
    public static final String TRANSFORM_XML_URI = "transformXml";
    public static final String PATH_TO_XSL = "path.to.xsl";

    @Override
    public void configure() {
        from("direct:" + TRANSFORM_XML_URI)
                .to("xslt:file:{{" + PATH_TO_XSL + "}}")
                .convertBodyTo(String.class);
    }
}