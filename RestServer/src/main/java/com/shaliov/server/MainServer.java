package com.shaliov.server;


import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 *
 * @author ShaliovArtiom, TruntsVitalij
 */

@ApplicationPath("/")
public class MainServer extends ResourceConfig {
    public MainServer() { packages("com/shaliov/server/service"); }
}
