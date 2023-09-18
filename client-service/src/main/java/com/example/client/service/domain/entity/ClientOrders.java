package com.example.client.service.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client_orders")
public class ClientOrders {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "order_id")
    private Long orderId;

    public ClientOrders(Long clientId, Long orderId) {
        this.clientId = clientId;
        this.orderId = orderId;
    }
}
