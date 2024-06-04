package org.example;

import io.smallrye.mutiny.Uni;

class Publisher {

    public Uni<String> publish() {
        return Uni.createFrom().item("Hello");
    }
}

class Subscriber {
    Publisher publisher = new Publisher();

    public void subscribe() {
        publisher.publish().subscribe().with(data -> System.out.println(data));
    }
}


public class PublisherAndSubscriber {
    public static void main(String[] args) {
        Subscriber subscriber = new Subscriber();
        subscriber.subscribe();

    }
}
