package ru.qwonix.test.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.qwonix.test.DocumentTransformationHistoryService;
import ru.qwonix.test.entity.DocumentTransformationHistory;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CamelTest extends CamelTestSupport {

    @Mock
    DocumentTransformationHistoryService mock;

    @EndpointInject()
    private MockEndpoint mockOutput;

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new StoreCamelRoute();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();

        camelContext.getPropertiesComponent().setLocation("classpath:camel.properties");
        return camelContext;
    }

    @Test
    public void testStoreCamelRoute() throws Exception {
        // Define your test here
        template.sendBody("direct:saveToDatabase", "Test Message"); // Send a test message

        // Get a reference to the mock endpoint
        MockEndpoint mockEndpoint = getMockEndpoint("mock:result");

        // Assert that the mock endpoint received the expected message
        mockEndpoint.expectedBodiesReceived("Expected Result");

        // Wait for the assertions to pass (you can specify a timeout if needed)
        assertMockEndpointsSatisfied();
    }

    @Test
    public void testRoute() throws Exception {
        DocumentTransformationHistory transformationHistory = mock(DocumentTransformationHistory.class);
        when(mock.save(transformationHistory)).thenReturn(new DocumentTransformationHistory());

        MockEndpoint mockEndpoint = getMockEndpoint("mock:bean:documentTransformationHistoryService");
        mockEndpoint.expectedMessageCount(1);
        mockEndpoint.expectedBodiesReceived(transformationHistory);

        template.sendBody("direct:saveToDatabase", transformationHistory);

        verify(mock).save(transformationHistory);
        assertMockEndpointsSatisfied();
    }

}
