package org.acme.jpa.entity.init;


import io.quarkus.runtime.StartupEvent;
import org.acme.jpa.entity.Datatypes;
import org.acme.jpa.entity.repository.DatatypesRepository;
import org.acme.resource.security.jpa.User;
import org.jboss.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Singleton
public class EntitiesStartupInit {

    @Inject
    Logger log;

    @Inject
    EntityManager em;

    @Inject
    DatatypesRepository repository;

    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        log.info ("EntitiesStartupInit initialization called ");
        // reset and load all test users
        //repository.deleteAllDatatypes();

        //Datatypes dt = new Datatypes();
        //em.persist(dt);
    }




}

