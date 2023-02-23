package org.acme.beans2;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

public class PBProducer {

    private static Logger log = Logger.getLogger(PBProducer.class);


    @ConfigProperty(name = "at.cgs.training.produceBean", defaultValue = "a")
    String beantoProduce;

    @Produces
    @RequestScoped
    PBInterface producePB() {
        log.info("producer called");
        if( "a".equalsIgnoreCase(beantoProduce)){
            return new PBImplA();
        }
        return new PBImplB();
    }

}



