package org.acme.cdi.beans2;

import org.jboss.logging.Logger;

public class PBImplA implements PBInterface  {

    private static Logger log = Logger.getLogger(PBImplA.class);

    @Override
    public String echo(String input) {
        log.info("PBImplA Implementierung A");
        return input + " -- from PBImplA";
    }

}
