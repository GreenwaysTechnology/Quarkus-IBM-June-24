package com.ibm.eventsourcing.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "events")
public class EventStore {
    @Id
    @GeneratedValue
    public long eventId;
    public String eventType;
    public String entityId;
    public String eventData;
    public LocalDateTime eventTime;
}