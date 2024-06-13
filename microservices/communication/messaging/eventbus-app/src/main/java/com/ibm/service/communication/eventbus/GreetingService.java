package com.ibm.service.communication.eventbus;

import io.quarkus.vertx.ConsumeEvent;
import io.vertx.mutiny.core.eventbus.Message;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    @ConsumeEvent("greeting")
    public String consume(String name) {
        return name.toUpperCase();
    }

    @ConsumeEvent("sink")
    public void process(Message<String> msg) {
        System.out.println("Address : " + msg.address());
        System.out.println("Body " + msg.body());
    }
}
