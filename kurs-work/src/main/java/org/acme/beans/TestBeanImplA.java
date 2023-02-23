package org.acme.beans;

import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.Serializable;

@RequestScoped
public class TestBeanImplA implements TestBean, Serializable {
    @Inject
    Logger log;

    @PostConstruct
    public void postConstruct() {
        log.info("postConstruct called ");
    }

    public String echo(String input) {
        log.info("TestBeanImplA");
        return echoReverse(input);
    }

    public String echoReverse(String input) {
        log.info("TestBeanImplA");
        StringBuilder inputSB = new StringBuilder();
        StringBuilder reverse = inputSB.append(input).reverse();
        return reverse.toString();
    }



}
