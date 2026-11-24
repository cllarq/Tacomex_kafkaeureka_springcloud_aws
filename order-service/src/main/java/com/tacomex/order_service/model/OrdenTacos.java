package com.tacomex.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="ordenes_tacos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdenTacos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;
    private String tipoTaco;
    private Integer cantidad;
    private String estado;
    private LocalDateTime fechaCreacion;
}
