package com.ssafy.api.dto.notification;

import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class NotificationData {

    private String title = "";
    private String body = "";

    @Override
    public String toString() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", this.title);
        jsonObject.addProperty("body", this.body);

        return jsonObject.toString();
    }
}

