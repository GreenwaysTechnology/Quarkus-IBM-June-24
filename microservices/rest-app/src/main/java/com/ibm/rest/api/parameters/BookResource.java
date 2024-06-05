package com.ibm.rest.api.parameters;

import jakarta.ws.rs.*;

@Path("books")
public class BookResource {

    @GET
    @Path("{Id}")
    public String findById(@PathParam("Id") Long id) {
        return "Book By Id " + id;
    }

    @Path("pages")
    @GET
    public String getPagesAndUnits(@QueryParam("totalPages") @DefaultValue("1") Long pages, @QueryParam("unit") @DefaultValue("0") Long unit) {
        return "Total Pages " + pages + " " + " Total Units " + unit;
    }

    @GET
    @Path("info")
    public String getBookInfo(@MatrixParam("category") @DefaultValue("science") String category, @MatrixParam("lang") @DefaultValue("english") String lang) {
        return "Category " + category + " " + "Language " + lang;

    }
}
