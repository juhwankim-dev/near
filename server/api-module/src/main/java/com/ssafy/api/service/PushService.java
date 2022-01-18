package com.ssafy.api.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ssafy.api.dto.notification.NotificationData;
import com.ssafy.api.dto.notification.NotificationRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PushService {
    private FirebaseApp firebaseApp;

    // application yml 설정파일에 설정한 값 사용
    @Value("${app.firebase-config}")
    private String firebaseConfig;



    @PostConstruct
    private void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream())).build();

            if (FirebaseApp.getApps().isEmpty()) {
                this.firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                this.firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {
            log.error("Create FirebaseApp Error", e);
        }
    }



    /**
     * fcm 토큰값으로 푸쉬 알림 전송
     * @param msgDTO
     */
    public String sendPushToDevice(NotificationRequestDTO msgDTO){
        String response = null;

        try{
            if(msgDTO != null){
                NotificationData notificationData = msgDTO.getNotification();

                Message message = Message.builder()
                        .setToken(msgDTO.getRegistration_ids())
                        .setNotification(new Notification(notificationData.getTitle(), notificationData.getBody()))
                        .putData("content", notificationData.getTitle())
                        .putData("body", notificationData.getBody())
                        .build();

                response = FirebaseMessaging.getInstance().send(message);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }
}
