package com.app.cloudStorage;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Container
	static MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>("mariadb:latest")
			.withDatabaseName("cloud_storage_db")
			.withUsername("main")
			.withPassword("12345qwert");

	@DynamicPropertySource
	static void configureDatabase(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mariaDBContainer::getUsername);
		registry.add("spring.datasource.password", mariaDBContainer::getPassword);
	}

}
