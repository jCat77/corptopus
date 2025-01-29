package com.jcat.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Component
@ConfigurationProperties("com.jcat.app")
@Validated
@Getter
@Setter
@ToString
public class ApplicationProperties {

    @Valid
    private C1 c1 = new C1();

    @Getter
    @Setter
    @Valid
    public static class C1 {

        @Valid
        private OData oData = new OData();

        @Getter
        @Setter
        public static class OData {

            @NotEmpty
            private String host;

            private Integer port;
            @NotEmpty
            private String proto;
            @NotEmpty
            private String service;
            @NotEmpty
            private String user;
            @NotEmpty
            private String password;
        }
    }
}
