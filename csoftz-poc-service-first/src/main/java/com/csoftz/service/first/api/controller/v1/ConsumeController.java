/*----------------------------------------------------------------------------*/
/* Source File:   CONSUMECONTROLLER.JAVA                                      */
/* Copyright (c), 2021 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jun.26/2021  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.service.first.api.controller.v1;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

/**
 * REST API for Consume Controller.
 *
 * @author COQ- Carlos Adolfo Ortiz Quirós
 * @version 1.1
 * @since 16 (JDK)
 */
@Log4j2
@RestController
@RequestMapping("/api/v1/consume")
public class ConsumeController {

    private static final String MSG_REQUEST_HEADERS = "Request Headers:[";
    private static final String MSG_RESPONSE_HEADERS = "Response Headers: [";
    private static final String SQUARE_BRACKET_RIGHT = "]";

    /**
     * Retrieves a message.<br>
     * GET /api/v1/consume/callme
     *
     * @return A String with the response.
     */
    @GetMapping("/callme")
    public String consumeMainSvc() {
        return "This is the call to the consumer";
    }

    /**
     * Takes from request and respponse the headers.<br>
     * GET /api/v1/consume/take/headers
     *
     * @param httpRequest  Reference to the Server HTTP Request.
     * @param httpResponse Reference to the Server HTTP Response.
     * @return A string containing the received request headers and any resposne headers added.
     */
    @GetMapping("/take/headers")
    public Mono<String> takeHeadersBack(ServerHttpRequest httpRequest, ServerHttpResponse httpResponse) {
        HttpHeaders httpResponseHeaders = httpResponse.getHeaders();
        httpResponseHeaders.add("take-hdr-add", "This is added in the endpoint");

        return Mono.just(
            MSG_REQUEST_HEADERS +
                httpRequest.getHeaders().toSingleValueMap().toString() +
                SQUARE_BRACKET_RIGHT +
                MSG_RESPONSE_HEADERS +
                httpResponseHeaders.toSingleValueMap().toString() +
                SQUARE_BRACKET_RIGHT);
    }

    /**
     * Returns data and adds a response header.<br>
     * GET /api/v1/consume/response-entity
     *
     * @return The requested data to the user.
     * @see Mono
     * @see ResponseEntity
     */
    @GetMapping("response-entity")
    public Mono<ResponseEntity<String>> setWithResponseEntityBuilder() {
        // See: https://www.baeldung.com/spring-response-header
        String responseHeaderKey = "Baeldung-Example-Header";
        String responseHeaderValue = "Value-ResponseEntityBuilder";
        String responseBody = "Response with header using ResponseEntity (builder)";

        return Mono
            .just(ResponseEntity.ok()
                .header(responseHeaderKey, responseHeaderValue)
                .header("response-time", LocalDateTime.now(ZoneOffset.UTC).toString())
                .body(responseBody));
    }

    /**
     * Returns data and adds a response header.<br>
     * GET /api/v1/consume/response-server
     *
     * @param response A reference to the Server HTTP Response (participating only in Spring WebFlux Apps).
     * @return A string with the data the requester expects.
     * @see Mono
     * @see ServerHttpResponse
     */
    @GetMapping("response-server")
    public Mono<String> setServerResponse(ServerHttpResponse response) {
        response.getHeaders().add("Baeldung-Example-Header", "Value-ServerHttpResponse");
        return Mono.just("Response with header using ServerHttpResponse");
    }
}
