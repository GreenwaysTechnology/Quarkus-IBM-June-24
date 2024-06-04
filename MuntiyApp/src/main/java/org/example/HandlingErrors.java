package org.example;

import io.smallrye.mutiny.Uni;

public class HandlingErrors {
    public static void main(String[] args) {
        Uni.createFrom().failure(new RuntimeException("oops"))
                .onFailure().recoverWithItem("fallback")
                .onItem().transform(data->data.toString().toUpperCase())
                .subscribe().with(data->{
                    System.out.println(data);
                },err->{
            System.out.println(err);
        });
    }
}
