package org.acme.service;
import io.quarkus.runtime.util.StringUtil;
import org.acme.entity.ChatMessageEntity;
import org.acme.entity.TestEntity;
import org.acme.logintercept.Logged;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ChatMessageServiceDb extends TestEntityServiceDbBase {
    @Transactional
    public List<ChatMessageEntity> loadAllMessages() {
        Query query = em.createQuery("select e from ChatMessageEntity e");
        return query.getResultList();
    }

    public ChatMessageEntity readChatMessage(Long id) {
        // direkte Verwendung der find methode des Entity Managers für eine ID
        ChatMessageEntity chatMessageEntity = em.find(ChatMessageEntity.class, id);
        return chatMessageEntity;
    }

    @Transactional
    public ChatMessageEntity create(ChatMessageEntity createCM ) {

        createCM.setCreationTime(LocalDateTime.now());
        createCM.setId(null);

        em.persist(createCM);

        return createCM;
    }

    @Transactional
    public ChatMessageEntity update(ChatMessageEntity entity) {

        // in realen Anwendungen sollte man das Merge nur dazu verwenden detached entities wieder dem
        // Entity Manager zu attachen.
        // und das update merge so implementieren dass das entity gelesen wird,
        // und danach dem Attached entity die Felder aktualisiert werden

        ChatMessageEntity mergedEntity = em.merge(entity);

        return mergedEntity;
    }


    @Transactional
    public String createChatMessageDBAndReturnCount(String chatMessage) {

        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setChatMessage(chatMessage);
        entity.setChatRoom("room1");
        entity.setUserName("username");
        LocalDateTime now = LocalDateTime.now();
        entity.setCreationTime(now);

        em.persist(entity);

        // Query query = getEm().createNamedQuery("ChatMessageEntity.countAll", Long.class);

        Query query = getEm().createQuery(
                        "SELECT count(e) from ChatMessageEntity e WHERE e.chatMessage like :chatMessageLike" );
        query.setParameter("chatMessageLike", "%new%");
        Long singleResult = (Long) query.getSingleResult();
        return "count is: " + singleResult.toString();
    }

    @Transactional
    @Logged
    public List<ChatMessageEntity> findChatMessagesWithLikeNameAndOrdedByDate(String likeStatement) {
        // suche alle chat messages and order by date descending

        Query query = getEm().createQuery(
                "SELECT e from ChatMessageEntity e " +
                        "WHERE e.chatMessage like :chatMessageLike " +
                        "ORDER by e.creationTime DESC" );
        if( !StringUtil.isNullOrEmpty(likeStatement)) {
            query.setParameter("chatMessageLike", likeStatement);
        } else {
            query.setParameter("chatMessageLike", "%");
        }

        List resultList = query.getResultList();
        return resultList;
    }

    @Transactional
    public List<ChatMessageEntity>
        findChatMessagesWithLikeNameAndOrdedByDateWithQueryBuilder(String likeStatement) {

        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<ChatMessageEntity> criteriaQuery = cb.createQuery(ChatMessageEntity.class);

        Root<ChatMessageEntity> from = criteriaQuery.from(ChatMessageEntity.class);

        criteriaQuery.select( from );

        Predicate like = cb.like(from.get("chatMessage"), likeStatement);
        criteriaQuery.where( like );
        Order orderByCT = cb.desc(from.get("creationTime"));
        criteriaQuery.orderBy(orderByCT );

        Query query = getEm().createQuery(criteriaQuery);
        List<ChatMessageEntity> resultList = query.getResultList();
        return resultList;
    }


    public Long countChatMessages() {

        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<ChatMessageEntity> from = query.from(ChatMessageEntity.class);
        Expression<Long> count = cb.count(from);
        query.select( count );

        // cq.where(/*your stuff*/);

        TypedQuery<Long> query1 = getEm().createQuery(query);
        Long singleResult = query1.getSingleResult();
        return singleResult;
    }

}
