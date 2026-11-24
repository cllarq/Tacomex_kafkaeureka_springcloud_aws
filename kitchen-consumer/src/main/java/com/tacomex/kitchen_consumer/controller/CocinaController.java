package com.tacomex.kitchen_consumer.controller;


import com.tacomex.kitchen_consumer.dto.OrdenEvento;
import com.tacomex.kitchen_consumer.service.CocinaConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/kitchen")
@RequiredArgsConstructor
public class CocinaController {

    private final CocinaConsumerService cocinaService;

    @GetMapping("listos")
    public List<OrdenEvento> listarPedidosTerminados(){
        return cocinaService.obtenerPedidosListos();
    }
}





























