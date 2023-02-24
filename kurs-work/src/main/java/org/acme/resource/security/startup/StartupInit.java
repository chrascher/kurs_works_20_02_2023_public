package org.acme.resource.security.startup;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import io.quarkus.runtime.StartupEvent;
import org.acme.resource.security.jpa.User;
import org.jboss.logging.Logger;


@Singleton
public class StartupInit {

    @Inject
    Logger log;

        @Transactional
        public void loadUsers(@Observes StartupEvent evt) {
            log.info ("StartupInit initialization called ");
            // reset and load all test users
            User.deleteAll();
            User.add("admin", "admin", "admin");
            User.add("user", "user", "user");
        }
    }
