package com.shaliov.client.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Класс, который позволяет клиенту подключаться к серверу.
 */
public class ClientConnection {
    private WebResource service;
    private static ClientConnection instance = new ClientConnection();

    private ClientConnection() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        service = client.resource(getBaseURI());
    }

    public WebResource getService() {
        return service;
    }

    public static ClientConnection getInstance() {
        return instance;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/").build();
    }
}
