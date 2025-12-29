package com.opencode.alumxbackend.chat.model;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//conversation between two users
@Entity
@Table(name = "chats",
        indexes = {
                @Index(name = "idx_user1_username", columnList = "user1Username"),
                @Index(name = "idx_user2_username", columnList = "user2Username")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_chat_users", columnNames = {"user1Username", "user2Username"})
        }
        
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long chatID;
    @Column(nullable = false)
    private String user1Username;
    @Column(nullable = false)
    private String user2Username;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
}
