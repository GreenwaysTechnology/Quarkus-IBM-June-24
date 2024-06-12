package com.ibm.rest.client.registry;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class BeanRegistry {
    //Consul server Host
    @ConfigProperty(name = "consul.host")
    String host;
    @ConfigProperty(name = "consul.port")
    int port;
    //service host and port
    @ConfigProperty(name = "hello-service-port", defaultValue = "9000")
    int helloPort;

    public void init(@Observes StartupEvent event, Vertx vertx) {
        ConsulClient client = ConsulClient.create(vertx,
                new ConsulClientOptions().setHost(host).setPort(port));
        //Register service with Consul Server
        client.registerService(new ServiceOptions()
                .setId("hello")
                .setName("hello-service")
                .setAddress("localhost")
                .setPort(helloPort)
        );
    }
}
