package org.acme.mockito;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.businessservice.ChatMessageServiceBl;
import org.acme.entity.ChatMessageEntity;
import org.acme.service.ChatMessageServiceDb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TestWithMock {

    @InjectMock
    ChatMessageServiceDb chatMsgDbMock;

    @Inject
    ChatMessageServiceBl toTest;

    private ChatMessageEntity chatMessage;

    private ChatMessageEntity chatMessageInput;

    /**
     * return a dummy chat message
     * @return
     */
    private ChatMessageEntity returnDummyChatMessage() {
        return chatMessage;
    }


    @BeforeEach
    void setUp() {

        ChatMessageEntity entityInput = new ChatMessageEntity();
        entityInput.setChatMessage("mocktest");
        entityInput.setChatRoom("mocktest");
        entityInput.setUserName( "mocktest");
        chatMessageInput = entityInput;

        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setId(1000L);
        entity.setChatMessage("returned_mocktest");
        entity.setChatRoom("mocktest");
        entity.setUserName( "mocktest");
        entity.setCreationTime(LocalDateTime.now());
        chatMessage = entity;

    }
    @Test
    public void testMockitoDummyReturnwithToReturn() {

        Mockito.when(chatMsgDbMock.create(chatMessageInput)).thenReturn(chatMessage);

        // call tested service but data access will be replaced with mocked one
        ChatMessageEntity created = toTest.create(chatMessageInput);

        assertEquals("returned_mocktest", created.getChatMessage());
        assertEquals(1000L, created.getId());
        assertNotNull(created.getCreationTime());
    }

    @Test
    public void testMockWithException() {

        Mockito.when(chatMsgDbMock.create(chatMessageInput)).thenThrow( new RuntimeException("my_exc"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            toTest.create(chatMessageInput);
            }, "Runtime exception was expected");


        assertEquals("my_exc", exception.getMessage());

    }


    @Test
    public void testMockWithAnswer() {

        Mockito.when(chatMsgDbMock.create(chatMessageInput))
                .thenAnswer( I -> returnDummyChatMessage());

        // call tested service but data access will be replaced with mocked one
        ChatMessageEntity created = toTest.create(chatMessageInput);

        assertEquals("returned_mocktest", created.getChatMessage());
        assertEquals(1000L, created.getId());
        assertNotNull(created.getCreationTime());

    }

}
