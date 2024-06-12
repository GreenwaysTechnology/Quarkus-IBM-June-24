package com.ibm.fault.timeout;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.util.List;

@Path("timeout")
public class TimeoutResource {
    @Inject
    TimeoutService timeoutService;

    @GET
    @Timeout(1000)
    @Fallback(fallbackMethod = "fallback")
    public List<String> getProducts() throws InterruptedException {
        return timeoutService.getProducts();
    }

    public List<String> fallback() {
        return List.of("Fallback Product");
    }
}
