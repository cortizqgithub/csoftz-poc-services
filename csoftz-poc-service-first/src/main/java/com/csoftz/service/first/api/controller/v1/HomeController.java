/*----------------------------------------------------------------------------*/
/* Source File:   HOMECONTROLLER.JAVA                                         */
/* Copyright (c), 2021 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jun.26/2021  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.service.first.api.controller.v1;

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
     * Retrieves an index request.<br/>
     * GET /api/v1/home
     *
     * @return A String indicating this services was involved in a request.
     */
    @GetMapping("")
    public Mono<String> retrieveIndex() {
        return Mono.just("This poc-service-first");
    }
}
