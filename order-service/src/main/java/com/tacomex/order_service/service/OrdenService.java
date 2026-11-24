package com.tacomex.order_service.service;

import com.tacomex.order_service.model.OrdenTacos;
import com.tacomex.order_service.repository.OrdenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdenService {

    private final OrdenRepository ordenRepository;
    private final KafkaTemplate<String, OrdenTacos> kafkaTemplate;

    public static final String TOPICO_PEDIDOS = "pedidos-tacos";

    public OrdenTacos crearOrden(String cliente, String tipoTaco, Integer cantidad) {

        OrdenTacos nuevaOrden = OrdenTacos.builder()
                .cliente(cliente)
                .tipoTaco(tipoTaco)
                .cantidad(cantidad)
                .estado("RECIBIDO")
                .fechaCreacion(LocalDateTime.now())
                .build();


        OrdenTacos ordenGuardada = ordenRepository.save(nuevaOrden);
        log.info("💾 Orden #{} guardada en PostgreSQL para {}", ordenGuardada.getId(), cliente);


        kafkaTemplate.send(TOPICO_PEDIDOS, ordenGuardada.getId().toString(), ordenGuardada);
        log.info("🚀 Orden #{} enviada al tópico de Kafka '{}'", ordenGuardada.getId(), TOPICO_PEDIDOS);

        return ordenGuardada;
    }

    public List<OrdenTacos> listarOrdenes() {
        return ordenRepository.findAll();
    }
}











































