package org.acme.cdi.beans3;

import org.acme.cdi.beans3.qualify.QualifyA;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@QualifyA
@ApplicationScoped
public class QBeanImplA implements QBean, Serializable {
    @Inject
    Logger log;

    @Override
    public String echo(String input) {
        log.info("QBeanImplA called");
        return input;
    }

}
