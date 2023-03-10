package org.acme.businessservice;

import org.acme.entity.ChatMessageEntity;
import org.acme.cdi.logintercept.Logged;
import org.acme.service.ChatMessageServiceDb;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class ChatMessageServiceBl {

    @Inject
    Logger log;

    @Inject
    ChatMessageServiceDb cmService;

    @Inject
    Validator validator;


    @Logged
    public ChatMessageEntity create(ChatMessageEntity createCM ) {

        // 3 Bean Validierungs Varianten sind nun verfügbar:
        //    1. AutomatischeValidierung des DTO via @Valid im Rest API
        //    2. Manuelle Validierung durch Aufruf im ChatMessageServiceBl
        //    3. Automatische Validierung des Entities bei der JPA/Hibernate Persistierung

        Set<ConstraintViolation<ChatMessageEntity>> violations = validator.validate(createCM);

        if ( !violations.isEmpty()) {
            StringBuffer error = new StringBuffer();
            for (ConstraintViolation<ChatMessageEntity> violation : violations) {
                log.error(violation.getMessage());
                error.append(violation.getMessage() + " : ");
            }
            throw new ConstraintViolationException(
                    new HashSet<ConstraintViolation<?>>(violations));

            // throw new MyBusinessException(error.toString());
        }

        return cmService.create(createCM);
    }

}
