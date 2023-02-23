package org.acme.beans2;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

public class PBImplA implements PBInterface  {

    private static Logger log = Logger.getLogger(PBImplA.class);

    @Override
    public String echo(String input) {
        log.info("PBImplA Implementierung A");
        return input + " -- from PBImplA";
    }

}
