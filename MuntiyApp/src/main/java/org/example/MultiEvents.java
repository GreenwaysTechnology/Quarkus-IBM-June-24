package org.example;

import io.smallrye.mutiny.Multi;

public class MultiEvents {
    public static void main(String[] args) {
        Multi.createFrom().items(1,2,3,4,5,6,7,8,9,10).subscribe().with(System.out::println);
    }
}
