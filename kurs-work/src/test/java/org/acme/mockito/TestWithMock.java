package org.acme.mockito;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.businessservice.ChatMessageServiceBl;
import org.acme.entity.ChatMessageEntity;
import org.acme.service.ChatMessageServiceDb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.time.LocalDateTime;

@QuarkusTest
public class TestWithMock {

    @InjectMock
    ChatMessageServiceDb mockecCMService;

    @Inject
    ChatMessageServiceBl toTest;

    private ChatMessageEntity chatMessage;

    @BeforeEach
    void setUp() {
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setId(1000L);
        entity.setChatMessage("mocktest");
        entity.setChatRoom("mocktest");
        entity.setUserName( "mocktest");
        entity.setCreationTime(LocalDateTime.now());
        chatMessage = entity;
    }
    @Test
    public void firstTest() {

        Mockito.when(mockecCMService.create(chatMessage)).thenReturn(chatMessage);

        ChatMessageEntity created = mockecCMService.create(chatMessage);

        Assertions.assertEquals("mocktest", created.getChatMessage());
    }

}
