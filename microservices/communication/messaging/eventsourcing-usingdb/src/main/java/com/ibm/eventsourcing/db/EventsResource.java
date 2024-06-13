package com.ibm.eventsourcing.db;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("events")
public class EventsResource {

    @Inject
    EventService eventService;

    @GET
    public List<EventStore> fetchAllEvents() {
        return eventService.fetchAllEvents();
    }
}
