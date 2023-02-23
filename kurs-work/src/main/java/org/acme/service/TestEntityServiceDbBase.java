package org.acme.service;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TestEntityServiceDbBase {
    @Inject
    protected Logger log;

    @Inject
    protected EntityManager em;

    protected EntityManager getEm() {
        return em;
    }
}
