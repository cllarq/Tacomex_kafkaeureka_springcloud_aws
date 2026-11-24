package com.tacomex.order_service.repository;

import com.tacomex.order_service.model.OrdenTacos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<OrdenTacos, Long> {
}
