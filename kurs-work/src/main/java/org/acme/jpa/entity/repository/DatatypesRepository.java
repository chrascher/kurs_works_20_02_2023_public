package org.acme.jpa.entity.repository;


import io.quarkus.runtime.StartupEvent;
import org.acme.jpa.entity.Datatypes;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class DatatypesRepository {

    @Inject
    EntityManager em;

    @Transactional
    public List<Datatypes> loadAllDattypes() {
        Query query = em.createQuery("SELECT d FROM Datatypes d");

        return query.getResultList();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public int deleteAllDatatypes() {
        Query query = em.createQuery("DELETE FROM Datatypes");
        // returns the number of deleted entities
        int i = query.executeUpdate();
        return i;
    }


}
