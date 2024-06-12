package com.ibm.rest.communication.caller;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.HttpResponse;
import io.vertx.mutiny.ext.web.client.WebClient;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("webclient")
public class WebClientResource {
    @Inject
    Vertx vertx;
    WebClient webClient;

    public void init(@Observes StartupEvent startupEvent) {
        //create instance of webClient object
        webClient = WebClient.create(vertx,
                new WebClientOptions()
                        .setDefaultHost("localhost")
                        .setDefaultPort(9000)
        );
    }

    @GET
    public Uni<String> findAll() {
        return webClient.get("/customers").send().onItem().transform(HttpResponse::bodyAsString);
    }

}
