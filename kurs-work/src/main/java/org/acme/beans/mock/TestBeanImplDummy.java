package org.acme.beans.mock;

import org.acme.beans.TestBean;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Alternative
@ApplicationScoped
public class TestBeanImplDummy implements TestBean, Serializable {

    @Inject
    Logger log;

    @Override
    public String echo(String input) {
        log.info("TestBeanImplDummy called");
        return "dummy info";
    }
}
