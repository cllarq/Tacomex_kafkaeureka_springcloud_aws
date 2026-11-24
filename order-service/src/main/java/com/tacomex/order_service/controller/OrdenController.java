package com.tacomex.order_service.controller;

import com.tacomex.order_service.model.OrdenTacos;
import com.tacomex.order_service.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdenTacos hacerPedido(@RequestBody PedidoRequest request) {
        return ordenService.crearOrden(request.cliente(), request.tipoTaco(), request.cantidad());
    }

    @GetMapping
    public List<OrdenTacos> obtenerTodosLosPedidos() {
        return ordenService.listarOrdenes();
    }

    public record PedidoRequest(String cliente, String tipoTaco, Integer cantidad) {}
}
