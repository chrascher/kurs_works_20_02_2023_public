package org.acme.resource.simple;


import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/helloMicroDemo")
public class DemoMicroResource {
    private static final Logger LOG = Logger.getLogger(DemoMicroResource.class);


    @ConfigProperty(name = "greeting.message", defaultValue = "d")
    String message;

    @GET
    @Path("/showMessage")
    @Produces(MediaType.TEXT_PLAIN)
    public String showMessage() {
        LOG.info(  "INFO  :: showMessage wurde aufgerufen");

        try {
            return "Hello: " + message;
        } catch (RuntimeException ex ) {
            LOG.error("fehler beim message lesen ", ex);
        }
        return "";
    }


}
