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
    ChatMessageServiceDb chatMsgDbMock;

    @Inject
    ChatMessageServiceBl toTest;

    private ChatMessageEntity chatMessage;

    private ChatMessageEntity chatMessageInput;

    @BeforeEach
    void setUp() {

        ChatMessageEntity entityInput = new ChatMessageEntity();
        entityInput.setChatMessage("mocktest");
        entityInput.setChatRoom("mocktest");
        entityInput.setUserName( "mocktest");
        chatMessageInput = entityInput;

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

        Mockito.when(chatMsgDbMock.create(chatMessageInput)).thenReturn(chatMessage);

        // call tested service but data access will be replaced with mocked one
        ChatMessageEntity created = toTest.create(chatMessageInput);

        Assertions.assertEquals("mocktest", created.getChatMessage());
        Assertions.assertEquals(1000L, created.getId());
        Assertions.assertNotNull(created.getCreationTime());
    }

}
