/*----------------------------------------------------------------------------*/
/* Source File:   HOMECONTROLLER.JAVA                                         */
/* Copyright (c), 2021 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jun.26/2021  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.service.main.api.controller.v1;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * REST API for Home Controller.
 *
 * @author COQ - Carlos Adolfo Ortiz Quirós
 * @version 1.1
 * @since 16 (JDK)
 */
@RestController
@RequestMapping("api/v1/home")
public class HomeController {

    /**
     * Processes the request by giving a hello message.<br/>
     * GET /api/v1/home/say/hello
     *
     * @return Hello message.
     */
    @GetMapping("say/hello")
    public Map<String, String> sayHello() {
        return Collections.singletonMap("message", "hello world");
    }

    /**
     * Retrieves information via ResponseEntity object.<br>
     * GET /api/v1/home/response-entity
     *
     * @return A string with message and a header in the response. The header is Baeldung-Example-Header.
     * @see Mono
     * @see ResponseEntity
     */
    @GetMapping("response-entity")
    public Mono<ResponseEntity<String>> setWithResponseEntityBuilder() {
        // See: https://www.baeldung.com/spring-response-header
        String responseHeaderKey = "Baeldung-Example-Header";
        String responseHeaderValue = "Value-ResponseEntityBuilder";
        String responseBody = "Response with header using ResponseEntity (builder)";

        return Mono.just(ResponseEntity.ok()
            .header(responseHeaderKey, responseHeaderValue)
            .header("response-time", LocalDateTime.now(ZoneOffset.UTC).toString())
            .body(responseBody));
    }

    /**
     * Retrieves information via ServerHttpResponse object.<br>
     * GET /api/v1/home/response-server
     *
     * @return A string with message and a header in the response. The header is Baeldung-Example-Header.
     * @see Mono
     * @see ServerHttpResponse
     */
    @GetMapping("response-server")
    public Mono<String> setServerResponse(ServerHttpResponse response) {
        response.getHeaders().add("Baeldung-Example-Header", "Value-ServerHttpResponse");
        return Mono.just("Response with header using ServerHttpResponse");
    }
}
