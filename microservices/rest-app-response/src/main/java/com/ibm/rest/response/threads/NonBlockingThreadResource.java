package com.ibm.rest.response.threads;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("nonblockingThread")
public class NonBlockingThreadResource {

    @GET
    public Uni<String> getName() {
        System.out.println(Thread.currentThread().getName());
        return Uni.createFrom().item("Subramnaian");
    }
}
