package org.acme.beans2;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

public class PBImplB implements PBInterface {

    private static Logger log = Logger.getLogger(PBImplB.class);

    @Override
    public String echo(String input) {
        log.info("PBImplB Implementierung B");
        return input + " -- from PBImplB";
    }

}
