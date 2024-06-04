package org.example;

import io.smallrye.mutiny.Multi;

public class MultiPipeLines {
    public static void main(String[] args) {
        Multi.createFrom().range(1, 25)
                .onItem().transform(data -> data * 2)
                .subscribe().with(System.out::println);

    }
}
