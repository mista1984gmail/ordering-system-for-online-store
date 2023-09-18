package com.example.client.service.service.impl;

import com.example.client.service.config.JDBCPostgreSQLConnection;
import com.example.client.service.domain.util.ClientUtil;
import com.example.client.service.domain.util.OrderStatus;
import com.example.client.service.domain.util.OrderUtil;
import com.example.client.service.service.ClientOrdersService;
import com.example.client.service.service.ClientService;
import com.example.client.service.service.dto.ClientDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClientOrdersServiceImpl implements ClientOrdersService {
    private final ClientService clientService;
    private final JDBCPostgreSQLConnection jdbcPostgreSQLConnection;

    @Override
    public ClientUtil getAllOrdersByClientIdAndStatus(Long id, String status) {
        Connection connection = null;
        ClientDto client = clientService.findById(id);
        ClientUtil clientUtil = ClientUtil.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .address(client.getAddress())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .build();
        List<OrderUtil> orders = new ArrayList<>();

        try {
            String selectTableSQL = "";
            connection = jdbcPostgreSQLConnection.getConnection();
            if(status==null){
                selectTableSQL = "select  o.description, o.order_status " +
                        "from orders o " +
                        "where  o.client_id = " + id;
            }
            else {
                selectTableSQL = "select  o.description, o.order_status " +
                        "from orders o " +
                        "where  o.client_id = " + id + " " +
                        "and o.order_status = " + "'" + status + "'";
            }
            PreparedStatement pstmt = connection.prepareStatement(selectTableSQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                OrderUtil orderUtil = new OrderUtil();
                String description = rs.getString("description");
                OrderStatus orderStatus = OrderStatus.valueOf(rs.getString("order_status"));

                orderUtil.setDescription(description);
                orderUtil.setOrderStatus(orderStatus);
                orders.add(orderUtil);
            }
            clientUtil.setOrders(orders);
        } catch (SQLException | IOException e) {
            log.error(e.getMessage());
        }
        finally {
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return clientUtil;
    }
}
