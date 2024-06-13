package com.ibm.eventsourcing.db;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StockRemovedEvent implements StockEvent {
    private Stock stockDetails;
}