package com.ssafy.core.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 2022-02-03 by 김영훈
 *
 * @author 김영훈
 * @version 0.0.1
 * @brief 북마크 class
 * @details 나만의 단어장을 위해 필요한 class
 * @date 2022-02-03
 */

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookmark",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"bookmark_key"}
                )
        }
)
public class Bookmark extends BaseEntity implements Serializable {

    //북마크 key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmark_key;

    //유저 ID
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    //수어 key
    @ManyToOne
    @JoinColumn(name = "handcontent_key")
    private Handcontent handcontent;


}
