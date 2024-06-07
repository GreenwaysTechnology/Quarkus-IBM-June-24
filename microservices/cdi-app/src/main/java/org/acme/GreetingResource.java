package org.acme;

import com.ibm.cdi.services.GoodByeService;
import com.ibm.cdi.services.GreetingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    //dependency injection
    // GreetingService greetingService = new GreetingService();
    @Inject
    GreetingService greetingService;

    @Inject
    GoodByeService goodByeService;

//    @Inject
//    public GreetingResource(GreetingService greetingService) {
//        this.greetingService = greetingService;
//    }
//    @Inject
//    public void setGreetingService(GreetingService greetingService) {
//        this.greetingService = greetingService;
//    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greetingService.hello() + "  " + goodByeService.sayGoodBye();
    }
}
