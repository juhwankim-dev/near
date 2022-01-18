package com.ssafy.api.dto.notification;

import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@Data
public class NotificationRequestDTO {

    private String registration_ids;
    private NotificationData notification;
    private NotificationData data;

}
