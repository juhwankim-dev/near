package com.ssafy.api.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    public enum Type{
        ENTER, COMM, START, END
    }

    private Type Type;
    private String roomId;
    private String sender;
    private String message;

}
