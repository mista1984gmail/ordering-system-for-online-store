package com.example.client.service.service.impl;

import com.example.client.service.config.JDBCPostgreSQLConnection;
import com.example.client.service.domain.entity.Client;
import com.example.client.service.service.JDBCClientService;
import com.example.client.service.service.dto.ClientDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.*;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class JDBCClientServiceImpl implements JDBCClientService {

    private final JDBCPostgreSQLConnection jdbcPostgreSQLConnection;

    @Override
    public Long save(ClientDto client) {
        Connection connection = null;
        Long idFromDB = 0L;
        try {
            connection = jdbcPostgreSQLConnection.getConnection();
            String insertTableSQL = "INSERT INTO clients"
                    + "(first_name, last_name, address, email, telephone) " + "VALUES "
                    + "(" + client.getFirstName() + ", "
                    + client.getLastName() + ", "
                    + client.getAddress() + ", "
                    + client.getEmail() + ", "
                    + client.getTelephone() + ") "
                    + " returning id";
            PreparedStatement pstmt = connection.prepareStatement(insertTableSQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                idFromDB = Long.parseLong(rs.getString("id"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return idFromDB;
    }
}
