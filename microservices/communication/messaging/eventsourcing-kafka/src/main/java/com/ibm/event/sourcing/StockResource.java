package com.ibm.event.sourcing;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("stock")
public class StockResource {

    @Inject
    StockRepo repository;
    @Inject
    EventService eventService;

    //addStock
    @POST
    @WithTransaction
    public Uni<Response> addStock(Stock stockRequest) throws JsonProcessingException {
        System.out.println(stockRequest);
        StockAddedEvent event = StockAddedEvent.builder().stockDetails(stockRequest).build();

        return repository.findByName(stockRequest.getName()).onItem().transformToUni(existingStockList -> {
            if (existingStockList != null && existingStockList.size() > 0) {
                System.out.println("Item  available");
                Stock existingStock = existingStockList.get(0);
                int newQuantity = existingStock.getQuantity() + stockRequest.getQuantity();
                existingStock.setQuantity(newQuantity);
                existingStock.setUserName(stockRequest.getUserName());
                String query = "quantity=" + existingStock.getQuantity() + " where name = ?1";
                System.out.println(query);
                return repository.update(query, existingStock.name).onItem().transform(entity -> {
                            try {
                                eventService.addEvent(event);
                                return Response.ok().status(200).entity(entity).build();
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }

                        }
                );
            } else {
                System.out.println("Item not available");
                return repository.persist(stockRequest).onItem().transform(entity ->
                        {
                            try {
                                eventService.addEvent(event);
                                return Response.ok().status(201).entity(entity).build();
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
            }
        });
    }

    //Stock remove
    @DELETE
    @WithTransaction
    public Uni<Response> removeStock(Stock stockRequest) throws JsonProcessingException {
        System.out.println(stockRequest);
        StockRemovedEvent event = StockRemovedEvent.builder().stockDetails(stockRequest).build();
        return repository.findByName(stockRequest.getName()).onItem().transformToUni(existingStockList -> {
            int newQuantity = 0;
            if (existingStockList != null && existingStockList.size() > 0) {
                System.out.println("Item  available");
                Stock existingStock = existingStockList.get(0);
                newQuantity = existingStock.getQuantity() - stockRequest.getQuantity();
                if (newQuantity <= 0) {
                    try {
                        repository.delete(existingStock).subscribe().with(res -> {
                            System.out.println("Done!!");
                        });
                        eventService.addEvent(event);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    //existingStock.setQuantity(newQuantity);
                    //existingStock.setUserName(stockRequest.getUserName());
                    String query = "quantity=" + newQuantity + " where name = ?1";
                    System.out.println(query);
                    return repository.update(query, existingStock.name).onItem().transform(entity -> {
                                try {
                                    eventService.addEvent(event);
                                    return Response.ok().status(200).entity(entity).build();
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                    );
                }
            }
            return Uni.createFrom().item("Removed").onItem().transform(res -> Response.ok().build());
        });


    }


    @GET
    public Uni<List<Stock>> findAll() {
        return repository.listAll();
    }


    @GET
    @Path("{name}")
    public Uni<List<Stock>> getStock(@PathParam("name") String name) throws JsonProcessingException {
        return repository.findByName(name);
    }

}
