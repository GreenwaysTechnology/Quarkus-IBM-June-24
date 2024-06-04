package org.example;

import io.smallrye.mutiny.Uni;

public class ErrorEvents {
    public static void main(String[] args) {
        Uni.createFrom().failure(new RuntimeException("oops")).subscribe().with(data->{},err->{
            System.out.println(err);
        });
    }
}
