package com.ssafy.core.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gameroom",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"id"}
                )
        }
)
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomId;

}
