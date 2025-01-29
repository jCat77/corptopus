package com.jcat.configuration;

import com.jcat.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.client.core.http.BasicAuthHttpClientFactory;
import org.apache.olingo.commons.api.format.ContentType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ODataConfiguration {

    @Bean
    public ODataClient getOdataClient(ApplicationProperties applicationProperties) {
        ODataClient client = ODataClientFactory.getClient();
        org.apache.olingo.client.api.Configuration configuration = client.getConfiguration();
        configuration.setDefaultPubFormat(ContentType.APPLICATION_JSON);
        configuration.setHttpClientFactory(
            new BasicAuthHttpClientFactory(
                applicationProperties.getC1().getOData().getUser(),
                applicationProperties.getC1().getOData().getPassword()
            )
        );
        return client;
    }

}
