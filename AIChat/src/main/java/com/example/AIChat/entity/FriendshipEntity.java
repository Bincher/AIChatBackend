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

    private String createdAt;

    private String status;

    private int userId;

    private int friendId;

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
