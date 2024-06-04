package org.example;

import io.smallrye.mutiny.Uni;

import java.time.Duration;

public class UniPipeLines {
    public static void main(String[] args) {
        Uni.createFrom().item("hello")
                .onItem().delayIt().by(Duration.ofMillis(1000))
                .onItem().transform(data -> data.toUpperCase())
                .subscribe().with(System.out::println);
    }
}
