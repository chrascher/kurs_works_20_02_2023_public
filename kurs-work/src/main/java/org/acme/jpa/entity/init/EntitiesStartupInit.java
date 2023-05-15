package org.acme.jpa.entity.init;


import io.quarkus.runtime.StartupEvent;
import org.acme.jpa.entity.Datatypes;
import org.acme.repository.DatatypesRepository;
import org.jboss.logging.Logger;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Random;

@Singleton
public class EntitiesStartupInit {

    @Inject
    Logger log;

    @Inject
    EntityManager em;

    @Inject
    DatatypesRepository repository;

    @Transactional
    public void loadUsers(@Observes @Priority(2) StartupEvent evt) {
        log.info ("EntitiesStartupInit initialization called ");
        // reset and load all test users

        repository.deleteAllDatatypes();

        // create some data
        Datatypes dt = new Datatypes();
        dt.setaLong(1L);
        dt.setaString("astring");
        em.persist(dt);
        Datatypes dt2 = new Datatypes();
        dt2.setaLong(new Random().nextLong());
        em.persist(dt2);

    }




}

