package org.acme.service;
import org.acme.entity.ChatMessageEntity;
import org.acme.entity.TestEntity;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@ApplicationScoped
public class TestEntityServiceDb extends TestEntityServiceDbBase {

    @Transactional
    public TestEntity createTestEntity() {
        log.infov("DemoServiceDb.createTestEntity");

        TestEntity te = new TestEntity();
        te.setUserName("test drei ");

        getEm().persist(te);

        // dieses Objekt ist noch im Entity Manager attached und diese Modifikation wird beim TX commit mit persistiert.
        te.setVorname("christian tx");

        log.infov("test entity: [{0}]", te.toString() );

        return te;
    } // nach dieser Funktion wird das TX Commit durchgeführt


    public Long countTestEntities() {

        CriteriaBuilder cb = getEm().getCriteriaBuilder();

        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        Root<TestEntity> from = query.from(TestEntity.class);

        Expression<Long> count = cb.count(from);

        query.select( count );

        // cq.where(/*your stuff*/);

        return getEm().createQuery(query).getSingleResult();
    }

}