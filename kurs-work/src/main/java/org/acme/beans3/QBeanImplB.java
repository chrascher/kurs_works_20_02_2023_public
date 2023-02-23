package org.acme.beans3;

import org.acme.beans.TestBean;
import org.acme.beans3.qualify.QualifyB;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
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
