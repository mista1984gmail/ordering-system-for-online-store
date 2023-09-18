package com.example.client.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class JDBCPostgreSQLConnection {
    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;
    public Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
