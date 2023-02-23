package org.acme.resource.testentity;


import org.acme.entity.TestEntity;
import org.acme.service.TestEntityServiceDb;
import org.acme.translator.ChatMessageTranslator;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * doku
 */
@Path("/testEntityResource")
public class TestEntityResource {

    @Inject
    Logger LOG;

    @Inject
    TestEntityServiceDb tmService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloSimple2() {
        LOG.info("info  message");

        // 1. erzeuge test entity
        TestEntity testEntity = tmService.createTestEntity();

        testEntity.setVorname("Ausserhalb TX");

        return "ChatMessageCount";
    }

}
