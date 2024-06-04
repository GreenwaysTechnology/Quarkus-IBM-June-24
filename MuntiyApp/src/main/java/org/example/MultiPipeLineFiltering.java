package org.example;

import io.smallrye.mutiny.Multi;

public class MultiPipeLineFiltering {
    public static void main(String[] args) {
        Multi.createFrom().range(1, 390)
                .filter(data -> data % 2 != 0)
                .select().first(10)
                .select().distinct()
                .subscribe().with(System.out::println);
    }
}
