package org.example;

import io.smallrye.mutiny.Uni;

public class HelloMutiny {
    public static void main(String[] args) {
        //Uni is publisher who can publish only one event
        Uni.createFrom().item("Hello").subscribe().with(data -> {
            System.out.println(data);
        });
    }
}
