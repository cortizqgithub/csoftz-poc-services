/*----------------------------------------------------------------------------*/
/* Source File:   FILENAME.JAVA                                               */
/* Copyright (c), YEAR CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 DATE  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.service.main.common.config.client.exchange;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;

import reactor.core.publisher.Mono;

/**
 * Add headers to the context in a reactive chain.
 *
 * @author COQ - Carlos Adolfo Ortiz Quirós
 * @version 1.1
 * @since 16 (JDK)
 */
@Component
public class HeaderExchange implements ExchangeFilterFunction {

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        return Mono.deferContextual(Mono::just)
            .flatMap(context -> {
                LinkedMultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
                Map<String, String> ctxMap = context.get("headers");
                ctxMap.forEach(valueMap::add);
                return next.exchange(
                    ClientRequest
                        .from(request)
                        .headers(httpHeaders -> httpHeaders.addAll(valueMap))
                        .build()
                );
            });
    }
}
