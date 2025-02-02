package com.jcat.configuration;

import com.jcat.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.client.core.http.DefaultHttpClientFactory;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class ODataConfiguration {

    @Bean
    public ODataClient getOdataClient(ApplicationProperties applicationProperties) {
        ODataClient client = ODataClientFactory.getClient();
        org.apache.olingo.client.api.Configuration configuration = client.getConfiguration();
        configuration.setDefaultPubFormat(ContentType.APPLICATION_JSON);

        final String auth = Base64.encodeBase64String(
            String.format("%s:%s",
                    applicationProperties.getC1().getOData().getUser(),
                    applicationProperties.getC1().getOData().getPassword())
                .getBytes(StandardCharsets.UTF_8));

        configuration.setHttpClientFactory(
            new DefaultHttpClientFactory() {
                @SuppressWarnings("deprecation")
                @Override
                public DefaultHttpClient create(HttpMethod method, URI uri) {
                    final DefaultHttpClient client = super.create(method, uri);
                    client.addRequestInterceptor((request, context) -> request.addHeader("Authorization", "Basic " + auth));
                    return client;
                }
            });

        return client;
    }

}
