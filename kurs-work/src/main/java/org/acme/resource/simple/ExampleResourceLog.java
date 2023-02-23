package org.acme.resource.simple;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloLog")
public class ExampleResourceLog {

    @Inject
    Logger LOG;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOG.infov("log infov from jboss param: [{0}] and param2: [{1}] and param 3 {2}.", "testInputParam", 42, System.currentTimeMillis());

        return "Hello Chris RESTEasy";
    }
}