package org.acme.requestscope;

import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@RequestScoped
public class RSBean {

    @Inject
    Logger log;

    private String requestScopedMessage = "default";

    @PostConstruct
    public void postConstruct() {
        log.info("postConstruct called: " + LocalDateTime.now());
    }


    public String getRequestScopedMessage() {
        return requestScopedMessage;
    }

    public void setRequestScopedMessage(String requestScopedMessage) {
        this.requestScopedMessage = requestScopedMessage;
    }

}
