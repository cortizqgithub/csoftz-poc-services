/*----------------------------------------------------------------------------*/
/* Source File:   CSOFTZPOCSERVICEFIRSTAPPLICATION.JAVA                       */
/* Copyright (c), 2021 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jun.26/2021  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.service.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point to the application.
 *
 * @author COQ - Carlos Adolfo Ortiz Quirós
 * @version 1.1
 * @since 16 (JDK)
 */
@SpringBootApplication
public class CsoftzPocServiceFirstApplication {

    /**
     * Main entry point to application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(CsoftzPocServiceFirstApplication.class, args);
    }

}
