package org.acme.resource.cdi;


import org.acme.beans.TestBean;
import org.acme.beans2.PBInterface;
import org.acme.beans3.QBean;
import org.acme.beans3.qualify.QualifyA;
import org.acme.beans3.qualify.QualifyB;
import org.acme.logintercept.RSBeanInterceptedExample;
import org.acme.requestscope.RSBean;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/cditests")
public class CDITestResource {

    @Inject
    TestBean tb;

    @Inject
    PBInterface bp;

    @Inject
    @QualifyB
    QBean qb;

    @Inject
    RSBean rsBean;

    @Inject
    RSBeanInterceptedExample rsBeanIE;

    @GET
    @Path("/alternative")
    @Produces(MediaType.TEXT_PLAIN)
    public String cditest() {
        String cditest = tb.echo("standard echo");
        return cditest;
    }

    @GET
    @Path("/producer")
    @Produces(MediaType.TEXT_PLAIN)
    public String producer() {
        String cditest = bp.echo("standard echo");
        return cditest;
    }

    @GET
    @Path("/qualify")
    @Produces(MediaType.TEXT_PLAIN)
    public String qualify() {
        String cditest = qb.echo("standard echo");
        return cditest;
    }

    @GET
    @Path("/requestScope")
    @Produces(MediaType.TEXT_PLAIN)
    public String requestScope() {
        rsBean.setRequestScopedMessage("Kontonummer zum Buchen: 1");
        String value = rsBean.getRequestScopedMessage();
        rsBean.setRequestScopedMessage("new value");

        return value;
    }

    @GET
    @Path("/interceptedBean")
    @Produces(MediaType.TEXT_PLAIN)
    public String interceptedBean() {

        rsBeanIE.echoReverse("Microservices");

        String echoReverse = rsBeanIE.echoReverse2("Microservices");

        return echoReverse;
    }

}
