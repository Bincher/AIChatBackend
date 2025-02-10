package com.example.AIChat.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "friendship")
@Table(name = "friendship")
public class FriendshipEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id") // DB 컬럼명과 매핑
    private Integer userId;

    @Column(name = "friend_id") // DB 컬럼명과 매핑
    private Integer friendId;

    @Column(name = "status") // DB 컬럼명과 매핑
    private String status;

    @Column(name = "created_at") // DB 컬럼명과 매핑
    private String createdAt;

    public FriendshipEntity(int userId, int friendId){

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sendingDate = simpleDateFormat.format(now);
        
        this.userId = userId;
        this.friendId = friendId;
        status = "PENDING";
        createdAt = sendingDate;
    }
}
