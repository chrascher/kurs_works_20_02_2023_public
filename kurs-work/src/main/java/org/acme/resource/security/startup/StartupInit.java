package org.acme.resource.security.startup;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import io.quarkus.runtime.StartupEvent;
import org.acme.resource.security.jpa.User;


@Singleton
public class StartupInit {
        @Transactional
        public void loadUsers(@Observes StartupEvent evt) {
            // reset and load all test users
            User.deleteAll();
            User.add("admin", "admin", "admin");
            User.add("user", "user", "user");
        }
    }
