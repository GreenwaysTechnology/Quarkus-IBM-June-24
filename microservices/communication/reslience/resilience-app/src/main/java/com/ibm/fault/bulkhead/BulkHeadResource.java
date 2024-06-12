package com.ibm.fault.bulkhead;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Fallback;

@Path("bulkhead")
public class BulkHeadResource {

    @GET
    @Bulkhead(value = 5)
    @Fallback(fallbackMethod = "fallback")
    public Response dontOverload() {
        System.out.println("Api is called");
        return Response.ok("bulk response").build();
    }

    public Response fallback() {
        System.out.println("Fallback response");
        return Response.ok("fallback").build();
    }
}
