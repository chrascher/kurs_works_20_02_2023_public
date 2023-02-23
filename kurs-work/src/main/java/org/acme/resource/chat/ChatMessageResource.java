package org.acme.resource.chat;


import org.acme.businessservice.ChatMessageServiceBl;
import org.acme.dto.ChatMessageDTO;
import org.acme.entity.ChatMessageEntity;
import org.acme.resource.chat.exception.MyBusinessException;
import org.acme.service.ChatMessageServiceDb;
import org.acme.translator.ChatMessageTranslator;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * doku
 */
@Path("/chatMessageResource")
public class ChatMessageResource {

    @Inject
    Logger LOG;

    @Inject
    ChatMessageServiceDb cmService;

    @Inject
    ChatMessageServiceBl cmServiceBl;

    @Inject
    ChatMessageTranslator translator;

    @GET
    @Path("/countChatMessages")
    @Produces(MediaType.TEXT_PLAIN)
    public String countChatMessages() {
        LOG.info("info  countChatMessages");
        Long aLong = cmService.countChatMessages();

        return aLong.toString();
    }

    @GET
    @Path("/chatMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ChatMessageDTO> chatMessageFindAll() {
        LOG.info("info  chatMessage ");
        List<ChatMessageDTO> result = new ArrayList<>();
        List<ChatMessageEntity> chatMessages = cmService.loadAllMessages();

        for(ChatMessageEntity chatMessage : chatMessages) {
            ChatMessageDTO chatMessageDTO = translator.toDTO(chatMessage);
            result.add(chatMessageDTO);
        }
        return result;
    }
    // z.b. http://localhost:8080/chatMessageResource/chatMessage/75
    @Operation( summary = "read a chat message",
            description = "read message with id ")
    @GET
    @Path("/chatMessage/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatMessageDTO readChatMessage(
            @Parameter(description = "id of the chat message")
            @PathParam("id") Long id) {

        if(id > 100000) {
            throw new MyBusinessException("id must be smaller then 100000");
        }

        ChatMessageEntity chatMessageEntity = cmService.readChatMessage(id);
        return translator.toDTO(chatMessageEntity);
    }

    @POST
    @Path("/chatMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatMessageDTO insertChatMessage(@Valid ChatMessageDTO chatMessageDTO) {
        LOG.info("insertChatMessage ");

        ChatMessageEntity entity = translator.fromDTO(chatMessageDTO);

        // ChatMessageEntity chatMessageEntity = cmServiceBl.create(entity);
        ChatMessageEntity chatMessageEntity = cmService.create(entity);

        return translator.toDTO(chatMessageEntity);
    }

    @PUT
    @Path("/chatMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatMessageDTO updateChatMessage(@Valid ChatMessageDTO chatMessageDTO) {
        LOG.info("updateChatMessage ");

        if(chatMessageDTO.getId() == null ) {
            throw new RuntimeException("id must not be null");
        }

        ChatMessageEntity entity = translator.fromDTO(chatMessageDTO);

        ChatMessageEntity chatMessageEntity = cmService.update(entity);

        return translator.toDTO(chatMessageEntity);
    }


    // z.b. http://localhost:8080/chatMessageResource/chatMessageQuery?msgtext=new
    @Operation( summary = "query Chat messages",
            description = "query with msgtext parameter and returned found chat messages")
    @GET
    @Path("/chatMessageQuery")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ChatMessageEntity> findAndReturnChatMessages(@QueryParam("msgtext") String msgtext) {
        LOG.infov("info  findAndReturnChatMessages: [{0}]", msgtext);
        String textWithPattern = java.text.MessageFormat.format("%{0}%", msgtext);
        List<ChatMessageEntity> cmListQB   = cmService.findChatMessagesWithLikeNameAndOrdedByDateWithQueryBuilder(textWithPattern);
        return cmListQB;
    }

    @GET
    @Path("/queryMessagesExamples")
    @Produces(MediaType.TEXT_PLAIN)
    public String findMessages() {
        LOG.info("info  findMessages");

        final String likePattern = "%new%";
        List<ChatMessageEntity> cmListJPQL = cmService.findChatMessagesWithLikeNameAndOrdedByDate( "MynewMessage");
        List<ChatMessageEntity> cmListQB   = cmService.findChatMessagesWithLikeNameAndOrdedByDateWithQueryBuilder(likePattern);

        return "list size: " + cmListJPQL.size() + " query Builder Result " + cmListQB.size();
    }

}
