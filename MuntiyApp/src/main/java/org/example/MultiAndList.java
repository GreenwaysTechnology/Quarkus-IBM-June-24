package org.example;

import io.smallrye.mutiny.Multi;

import java.util.List;

public class MultiAndList {
    public static void main(String[] args) {
        List myList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Multi.createFrom().iterable(myList).subscribe().with(data->{
            System.out.println(data);
        });
    }
}
