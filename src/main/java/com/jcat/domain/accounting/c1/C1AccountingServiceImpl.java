package com.jcat.domain.accounting.c1;

import com.jcat.domain.accounting.AccountingService;
import com.jcat.properties.ApplicationProperties;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.commons.api.edm.Edm;
import org.springframework.stereotype.Service;


@Service
public class C1AccountingServiceImpl implements AccountingService {

    private final ODataClient client;

    private final ApplicationProperties applicationProperties;

    private final String serviceUri;

    public C1AccountingServiceImpl(ODataClient client, ApplicationProperties applicationProperties) {
        this.client = client;
        this.applicationProperties = applicationProperties;
        ApplicationProperties.C1.OData oData = applicationProperties.getC1().getOData();
        serviceUri = oData.getProto() +
                "://" +
                oData.getHost() +
                (oData.getPort() == null ? "" : ":" + oData.getPort()) +
                oData.getService();
    }

    private Edm getEdm() {
        final Edm edm = client.getRetrieveRequestFactory().
                getMetadataRequest(serviceUri).execute().getBody();
        return edm;
    }

    @Override
    public void test() {
        getEdm();
    }
}
