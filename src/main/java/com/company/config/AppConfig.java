package com.company.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {

    @Value("${app.name}")
    private String name;

    @Value("${app.version}")
    private String version;

    @Value("${app.security.signing-key}")
    private String signingKey;

    @Value("${app.security.jwt.client-id}")
    private String clientId;

    @Value("${app.security.jwt.client-secret}")
    private String clientSecret;

    @Value("${app.security.jwt.grant-type}")
    private String grantType;

    @Value("${app.security.jwt.scope-read}")
    private String scopeRead;

    @Value("${app.security.jwt.scope-write}")
    private String scopeWrite;

    @Value("${app.security.jwt.resource-id}")
    private String resourceId;

}
