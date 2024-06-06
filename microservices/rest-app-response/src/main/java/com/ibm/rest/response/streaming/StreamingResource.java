package com.ibm.rest.response.streaming;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Path("stream")
public class StreamingResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Long> stream() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1));
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Path("list")
    public Multi<Integer> list() {
        System.out.println(Thread.currentThread().getName());
        //return Multi.createFrom().iterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Random random = new Random();
        return Multi.createFrom().iterable(List.of(1,2,3,4,5,6,7,8))
                .onItem().call(i -> {
                    Duration delay = Duration.ofMillis(10000);
                    return Uni.createFrom().nullItem().onItem().delayIt().by(delay);
                });
    }
}
