package org.acme.translator;


import org.acme.dto.ChatMessageDTO;
import org.acme.entity.ChatMessageEntity;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ChatMessageTranslator {

    @Inject
    Logger log;
    public ChatMessageDTO toDTO(ChatMessageEntity entity) {
            if(entity == null) {
                return null;
            }
            ChatMessageDTO dto = new ChatMessageDTO();
            dto.setId(entity.getId());
            dto.setChatMessage(entity.getChatMessage());
            dto.setChatRoom(entity.getChatRoom());
            dto.setUserName(entity.getUserName());
            dto.setCreationTime(entity.getCreationTime());
            dto.setImportant(entity.getImportant());
            return dto;
        }

    public ChatMessageEntity fromDTO(ChatMessageDTO chatMessageDTO) {
        if(chatMessageDTO == null) {
            return null;
        }
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setId(chatMessageDTO.getId());
        entity.setChatMessage(chatMessageDTO.getChatMessage());
        entity.setChatRoom(chatMessageDTO.getChatRoom());
        entity.setUserName(chatMessageDTO.getUserName());
        entity.setCreationTime(chatMessageDTO.getCreationTime());
        entity.setImportant(chatMessageDTO.getImportant());
        return entity;
    }
}