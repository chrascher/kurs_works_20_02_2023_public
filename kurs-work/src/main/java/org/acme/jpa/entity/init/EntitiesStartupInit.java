package org.acme.jpa.entity.init;


import io.quarkus.runtime.StartupEvent;
import org.acme.resource.security.jpa.User;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Singleton
public class EntitiesStartupInit {

    @Inject
    EntityManager em;

    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users

    }




}

