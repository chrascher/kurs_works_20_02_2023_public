package org.acme.cdi.beans3;

import org.acme.cdi.beans3.qualify.QualifyB;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@QualifyB
@ApplicationScoped
public class QBeanImplB implements QBean, Serializable {

    @Inject
    Logger log;

    @Override
    public String echo(String input) {
        log.info("QBeanImplB called");
        return "dummy";
    }
}
