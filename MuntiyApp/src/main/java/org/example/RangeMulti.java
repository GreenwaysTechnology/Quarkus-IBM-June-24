package org.example;

import io.smallrye.mutiny.Multi;

public class RangeMulti {
    public static void main(String[] args) {
        Multi.createFrom().range(1, 25).subscribe().with(System.out::println);
    }
}
