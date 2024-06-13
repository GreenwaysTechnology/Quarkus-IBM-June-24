package com.ibm.service.communication.eventbus;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("async")
public class EventResource {
    @Inject
    EventBus eventBus;

    @GET
    @Path("{name}")
    public Uni<String> greeting(@PathParam("name") String name) {
        return eventBus.<String>request("greeting", name).onItem().transform(Message::body);
    }
    @Path("fireandForget")
    @POST
    public void process(String payload) {
        eventBus.<String>requestAndForget("sink", payload);
    }
    @POST
    @Path("publish")
    public void publish(String message) {
        eventBus.publish("notification", message);
    }
}
