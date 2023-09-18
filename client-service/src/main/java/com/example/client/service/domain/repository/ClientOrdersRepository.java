package com.example.client.service.domain.repository;

import com.example.client.service.domain.entity.ClientOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientOrdersRepository extends JpaRepository<ClientOrders, Long> {
}
