package com.ibm.rest.response.threads;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("workerThread")
public class WorkerThreadResource {

    @GET
    public String runOnWorker() {
        String threadName = Thread.currentThread().getName();
        //on which thread this method is running
        return threadName;
    }
}
