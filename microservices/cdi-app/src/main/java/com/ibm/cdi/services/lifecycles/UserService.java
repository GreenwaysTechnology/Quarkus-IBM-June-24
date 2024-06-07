package com.ibm.cdi.services.lifecycles;

import io.quarkus.runtime.Shutdown;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import java.util.List;

@ApplicationScoped
public class UserService {

    List list;

    @Startup
    public void init() {
        list = List.of("Subramanian", "adming", "guest");
    }

    public void startUp(@Observes StartupEvent startupEvent) {
        System.out.println("Startup event");
    }


    public List<String> getUsers() {
        return list;
    }

    @Shutdown
    public void destory() {
        System.out.println("Clean up");
    }
}
