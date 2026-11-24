package com.tacomex.kitchen_consumer.service;

import com.tacomex.kitchen_consumer.dto.OrdenEvento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class CocinaConsumerService {

    private final List<OrdenEvento> pedidosListos = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = "pedidos-tacos", groupId = "cocina-grupo-1")
    public void recibirYPrepararOrden(OrdenEvento orden){

        log.info("👨‍🍳 [COCINA] ¡Nuevo pedido recibido! Orden #{}: {} tacos de {} para {}",
                orden.getId(), orden.getCantidad(), orden.getTipoTaco(), orden.getCliente());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        orden.setEstado("LISTO_PARA_ENTREGA");
        pedidosListos.add(orden);

        log.info("✅ [COCINA] ¡Orden #{} TERMINADA y lista para repartidor!", orden.getId());
    }

    public List<OrdenEvento> obtenerPedidosListos(){
        return new ArrayList<>(pedidosListos);
    }
}























