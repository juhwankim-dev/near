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
 * @brief 지문자 컨텐츠
 * @details 지문자 컨테츠 테이블
 * @date 2022-01-25
 */

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fingercontent",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"fingercotent_key"}
                )
        }
)
public class Fingercontent {

    //지어 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fingercotent_key;

    //자음 0, 모음 1
    @Column(nullable = false, length = 2)
    private int category;


    //지어 이름
    @Column(nullable = true, length = 2)
    private String name;

    // 이미지 경로
    @Column(nullable = true, length = 100)
    private String image_path;

    // 수어 설명
    @Column(nullable = true, length = 255)
    private String explanation;


    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime register_time;

    public void updateFingerContent_key(long fingercotent_key) {
        this.fingercotent_key = fingercotent_key;
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
