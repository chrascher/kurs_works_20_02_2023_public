package org.acme.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.acme.dto.ChatMessageDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ChatMessageResourceTest {


    @Test
    @Disabled
    public void readChatMessage() {

        ResponseBody body = given()
                .when().get("/chatMessageResource/chatMessage/10")
                .then()
                // .statusCode(204)
                .extract().response().body();

        ChatMessageDTO chatMessageDto = body.as(ChatMessageDTO.class);
        assertNotNull(chatMessageDto);
        System.out.println("chatMessageDto = " + chatMessageDto.toString());
    }

    @Test
    @Disabled
    public void createChatMessagePost() {

        ChatMessageDTO chatMessageDto = new ChatMessageDTO();
        chatMessageDto.setChatMessage("TEST via API Rest Assured");
        chatMessageDto.setChatRoom("testPutMessage");
        chatMessageDto.setUserName("testPutMessage");
        chatMessageDto.setCreationTime(null);

        RequestSpecification httpRequest  = given() // .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        RequestSpecification requestSpecification = httpRequest.body(chatMessageDto);

        Response response = requestSpecification.post("/chatMessageResource/chatMessage");

        //Fetching the response code from the request and validating the same
        System.out.println("The response code - " +response.getStatusCode());
        assertEquals(response.getStatusCode(),200);
    }

}
