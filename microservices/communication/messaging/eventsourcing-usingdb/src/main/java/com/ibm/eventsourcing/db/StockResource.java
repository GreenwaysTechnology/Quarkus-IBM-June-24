package com.ibm.eventsourcing.db;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonParseException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("stock")
public class StockResource {
    @Inject
    StockRepository stockRepository;

    @Inject
    EventService eventService;

    @POST
    @Transactional
    public Response createOrder(Stock stockRequest) throws JsonProcessingException {
        StockAddedEvent addedEvent = StockAddedEvent.builder().stockDetails(stockRequest).build();
        StockUpdatedEvent updatedEvent = StockUpdatedEvent.builder().stockDetails(stockRequest).build();

        List<Stock> existingStockList = stockRepository.findByName(stockRequest.getName());

        if (existingStockList != null && existingStockList.size() > 0) {

            Stock existingStock = existingStockList.get(0);

            int newQuantity = existingStock.getQuantity() + stockRequest.getQuantity();

            existingStock.quantity = newQuantity;
            existingStock.name = stockRequest.name;
            updatedEvent.setStockDetails(existingStock);
            //fire event : update
            eventService.addEvent(updatedEvent);
            return Response.ok().entity(existingStock).status(200).build();
        } else {
            stockRepository.persist(stockRequest);
            //fireEvent : add event
            eventService.addEvent(addedEvent);

            return Response.ok().entity(stockRequest).status(201).build();
        }
    }

    @DELETE
    @Transactional
    public void removeStock(Stock stock) throws JsonProcessingException {
        StockRemovedEvent event = StockRemovedEvent.builder().stockDetails(stock).build();

        int newQuantity = 0;

        List<Stock> existingStockList = stockRepository.findByName(stock.getName());

        if (existingStockList != null && existingStockList.size() > 0) {

            Stock existingStock = existingStockList.get(0);

            newQuantity = existingStock.getQuantity() - stock.getQuantity();

            if (newQuantity <= 0) {
                stockRepository.delete(existingStock);
            } else {
                existingStock.setQuantity(newQuantity);
                existingStock.setUser(stock.getName());
                stockRepository.persist(existingStock);
            }
            event.setStockDetails(existingStock);
        }

        eventService.addEvent(event);
    }

    @GET
    public List<Stock> findAll() {
        return stockRepository.listAll();
    }

    @GET
    @Path("name")
    public List<Stock> findByName(@QueryParam("name") String name) {
        return stockRepository.findByName(name);
    }
}
