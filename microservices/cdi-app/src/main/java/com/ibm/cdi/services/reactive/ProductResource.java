package com.ibm.cdi.services.reactive;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("products")
public class ProductResource {
    @Inject
    ProductService productService;

    @GET
    public Uni<List<String>> findAll() {
        return productService.getProducts();
    }
}
