package com.ibm.eventsourcing.db;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockUpdatedEvent implements StockEvent{
    private Stock stockDetails;
}

