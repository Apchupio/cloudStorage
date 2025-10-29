package com.app.cloudStorage.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Cloud Storage APi",
                description = "Documentation for Cloud Storage API",
                version = "1.0.0"
        ),
        servers = @Server(url = "http://localhost:8080")
)
public class OpenAPiConfig {
}
