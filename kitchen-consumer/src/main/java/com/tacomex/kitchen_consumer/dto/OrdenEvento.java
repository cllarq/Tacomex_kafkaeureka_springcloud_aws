package com.tacomex.kitchen_consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenEvento {
    private Long id;
    private String cliente;
    private String tipoTaco;
    private Integer cantidad;
    private String estado;
    private LocalDateTime fechaCreacion;
}
