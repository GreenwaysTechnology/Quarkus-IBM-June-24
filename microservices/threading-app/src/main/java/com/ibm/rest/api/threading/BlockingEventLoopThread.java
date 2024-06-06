package com.ibm.rest.api.threading;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.time.Duration;

@Path("blocking")
public class BlockingEventLoopThread {


    @GET
    @Blocking
    public Uni<String> blockMe() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        //Thread.sleep(1000);
        Thread.sleep(5000);
        return Uni.createFrom().item("Hello");
    }
    @GET
    @Path("reactivescheduler")
    public Uni<Response> getResponseWithDelay() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        return Uni.createFrom().item("Hello")
                .onItem()
                .transform(f -> Response.ok(f).header("message", "How are you"))
                .onItem()
                .delayIt().by(Duration.ofMillis(6000))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }
}
