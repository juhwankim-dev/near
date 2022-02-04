package com.ssafy.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 2022-01-25 by 김영훈
 *
 * @author 김영훈
 * @version 0.0.1
 * @brief 수어 컨텐츠
 * @details 수어 컨테츠 테이블
 * @date 2022-01-26
 */

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "handcontent",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"handcontent_key"}
                )
        }
)
public class Handcontent {

    //수어 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long handcontent_key;

    //수어 이름
    @Column(nullable = true, length = 100)
    private String name;

    // 수어 이미지 경로
    @Column(nullable = true, length = 255)
    private String image_path;

    //비디오 경로
    @Column(nullable = true, length = 255)
    private String video_path;

    // 수어 의미
    @Column(nullable = true, length = 255)
    private String mean;

    // 수어 설명
    @Column(nullable = true, length = 255)
    private String explanation;

    // 수어 동작
    @Column(nullable = true, length = 100)
    private String movement;
    
    // 생성 날짜
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime register_time;

    public void updateHandKey(long handcontent_key) {
        this.handcontent_key = handcontent_key;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateImage_path(String image_path) {
        this.image_path = image_path;
    }

    public void updateExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void updateRegister_time(LocalDateTime register_time) {
        this.register_time = register_time;
    }

}
