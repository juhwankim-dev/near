package com.ssafy.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {

    public enum MessageType{
        ENTER, COMM, START, END
    }

    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;

}
