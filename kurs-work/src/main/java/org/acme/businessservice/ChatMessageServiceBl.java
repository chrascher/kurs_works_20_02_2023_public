package org.acme.businessservice;

import org.acme.entity.ChatMessageEntity;
import org.acme.logintercept.Logged;
import org.acme.resource.chat.exception.MyBusinessException;
import org.acme.service.ChatMessageServiceDb;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
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

        createCM.setChatRoom("");


        Set<ConstraintViolation<ChatMessageEntity>> violations = validator.validate(createCM);

        if ( !violations.isEmpty()) {
            StringBuffer error = new StringBuffer();
            for (ConstraintViolation<ChatMessageEntity> violation : violations) {
                log.error(violation.getMessage());
                error.append(violation.getMessage() + " : ");
            }

            throw new MyBusinessException(error.toString());
        }

        return cmService.create(createCM);
    }

}
