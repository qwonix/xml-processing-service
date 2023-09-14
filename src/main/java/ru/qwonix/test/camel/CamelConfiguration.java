package ru.qwonix.test.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.qwonix.test.service.DocumentTransformationHistoryService;

@Configuration
public class CamelConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public CamelContext camelContext(
            FileProcessingCamelRoute camelRoute,
            StoreCamelRoute storeCamelRoute,
            XslTransformCamelRoute transformCamelRoute,
            DocumentTransformationHistoryService documentTransformationHistoryService) throws Exception {
        DefaultRegistry defaultRegistry = new DefaultRegistry();
        defaultRegistry.bind("documentTransformationHistoryService", documentTransformationHistoryService);

        DefaultCamelContext defaultCamelContext = new DefaultCamelContext(defaultRegistry);
        defaultCamelContext.getPropertiesComponent().setLocation("classpath:camel.properties");

        defaultCamelContext.addRoutes(camelRoute);
        defaultCamelContext.addRoutes(storeCamelRoute);
        defaultCamelContext.addRoutes(transformCamelRoute);

        return defaultCamelContext;
    }
}
