package ru.qwonix.test.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class XslTransformCamelRouteCamelRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new XslTransformCamelRoute();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();

        camelContext.getPropertiesComponent().setLocation("classpath:camel.properties");
        return camelContext;
    }

//    @Override
//    public boolean isUseAdviceWith() {
//        return true;
//    }

//    @Before
//    public void configureRoutes() throws Exception {
//        // Define route modifications for testing
//
//        AdviceWithRouteBuilder.adviceWith(context, "transformXml", a -> {
//            // Intercept the "to" endpoint and replace it with a mock endpoint
//            a.interceptSendToEndpoint("xslt:file:{{path.to.xsl}}")
//                    .skipSendToOriginalEndpoint()
//                    .to("mock:result");
//        });
//    }

    @Test
    public void testXslTransformCamelRoute() throws Exception {
        // Define the expected result after XSL transformation
        String expectedOutput = "Expected Result";

        // Get the mock endpoint for result validation
        MockEndpoint mockEndpoint = getMockEndpoint("mock:result");
        mockEndpoint.expectedBodiesReceived(expectedOutput);

        // Send a test message to the route
        ProducerTemplate template = context.createProducerTemplate();
        template.sendBody("direct:transformXml", readResource("/original_order.xml"));

        // Ensure that the assertions pass
        assertMockEndpointsSatisfied();
    }

    private String readResource(String resourcePath) {
        try {
            InputStream inputStream = new ClassPathResource(resourcePath).getInputStream();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("No file with name " + resourcePath, e);
        }
    }
}