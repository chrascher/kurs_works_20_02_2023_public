package org.acme.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author CGS-IT Solutions @2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatMessageDTO {

    private Long id;

    @NotBlank(message="ChatRoom may not be blank")
    private String userName;

    private String chatRoom;

    private String chatMessage;

    // @ JsonSerialize(using = LocalDateTimeSerializer.class)
    // @ JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime creationTime;

    private Boolean isImportant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        this.chatRoom = chatRoom;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getImportant() {
        return isImportant;
    }

    public void setImportant(Boolean important) {
        isImportant = important;
    }


    @Override
    public String toString() {
        return "ChatMessageDto{" +
                "userName='" + userName + '\'' +
                ", chatRoom='" + chatRoom + '\'' +
                ", chatMessage='" + chatMessage + '\'' +
                ", creationTime=" + creationTime +
                ", isImportant=" + isImportant +
                '}';
    }
}