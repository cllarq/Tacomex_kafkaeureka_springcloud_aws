package com.tacomex.api_gateway.config;


import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order-service-route")
                .route(RequestPredicates.path("/api/orders/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("ORDER-SERVICE"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> kitchenConsumerRoute() {
        return GatewayRouterFunctions.route("kitchen-consumer-route")
                .route(RequestPredicates.path("/api/kitchen/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("KITCHEN-CONSUMER"))
                .build();
    }
}

