/*----------------------------------------------------------------------------*/
/* Source File:   CONSUMECONTROLLER.JAVA                                      */
/* Copyright (c), 2021 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jun.26/2021  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.service.main.controller.api.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.csoftz.webflux.filter.common.config.client.exchange.HeaderExchange;

import reactor.core.publisher.Mono;

/**
 * REST API for Consume Controller.
 *
 * @author COQ - Carlos Adolfo Ortiz Quirós
 * @version 1.1
 * @since 16 (JDK)
 */
@RestController
@RequestMapping("api/v1/consume")
public class ConsumeController {
    private static final String POC_SERVICE_FIRST = "http://localhost:7346";
    private static final String POC_SERVICE_FIRST_CONSUME_CALLME = "/api/v1/consume/callme";
    private static final String POC_SERVICE_FIRST_CONSUME_TAKE_HEADERS = "/api/v1/consume/take/headers";

    /**
     * Consumes a poc-service-first endpoint to get the data the user needs.<br>
     * GET /api/v1/consume/callme
     *
     * @param headerExchange Necessary in Reactive Apps to pass headers to downstream services.
     * @return A String with the external call result.
     * @see HeaderExchange
     */
    @GetMapping("callme")
    public Mono<String> callPocServiceFirstConsumeMain(HeaderExchange headerExchange) {
        return WebClient
            .builder()
            .filter(headerExchange)
            .baseUrl(POC_SERVICE_FIRST)
            .build()
            .get()
            .uri(POC_SERVICE_FIRST_CONSUME_CALLME)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String.class);
    }

    /**
     * Consumes a poc-service-first endpoint to get the data the user needs, with request/response headers.<br>
     * GET /api/v1/consume/take/headers
     *
     * @param headerExchange Necessary in Reactive Apps to pass headers to downstream services.
     * @return A String with the external call result.
     * @see HeaderExchange
     */
    @GetMapping("take/headers")
    public Mono<String> callPocServiceFirstTakeHeaders(HeaderExchange headerExchange) {
        return WebClient
            .builder()
            .filter(headerExchange)
            .baseUrl(POC_SERVICE_FIRST)
            .build()
            .get()
            .uri(POC_SERVICE_FIRST_CONSUME_TAKE_HEADERS)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String.class);
    }
}