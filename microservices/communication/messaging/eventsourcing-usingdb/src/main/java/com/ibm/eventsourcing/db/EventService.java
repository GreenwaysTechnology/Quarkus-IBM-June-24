package com.ibm.eventsourcing.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class EventService {

    @Inject
    EventStoreRepository repository;

    public void addEvent(StockAddedEvent event) throws JsonProcessingException {
        EventStore eventStore = new EventStore();
        eventStore.setEventData(new ObjectMapper().writeValueAsString(event.getStockDetails()));
        eventStore.setEventType("STOCK_ADDED");
        eventStore.setEntityId(event.getStockDetails().getName());
        eventStore.setEventTime(LocalDateTime.now());
        //this will store every stock added event into table..
        repository.persist(eventStore);
    }

    public void addEvent(StockUpdatedEvent event) throws JsonProcessingException {
        EventStore eventStore = new EventStore();
        eventStore.setEventData(new ObjectMapper().writeValueAsString(event.getStockDetails()));
        eventStore.setEventType("STOCK_UPDATED");
        eventStore.setEntityId(event.getStockDetails().getName());
        eventStore.setEventTime(LocalDateTime.now());
        //this will store every stock added event into table..
        repository.persist(eventStore);
    }

    public void addEvent(StockRemovedEvent event) throws JsonProcessingException {
        EventStore eventStore = new EventStore();
        eventStore.setEventData(new ObjectMapper().writeValueAsString(event.getStockDetails()));
        eventStore.setEventType("STOCK_REMOVED");
        eventStore.setEntityId(event.getStockDetails().getName());
        eventStore.setEventTime(LocalDateTime.now());
        repository.persist(eventStore);
    }

    public List<EventStore> fetchAllEvents() {
        return repository.listAll();
    }

}
