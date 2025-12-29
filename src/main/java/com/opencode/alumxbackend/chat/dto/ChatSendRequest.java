package com.opencode.alumxbackend.chat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatSendRequest {
    @NotBlank(message = "Sender Id is required")
    private Long senderId;
    
    @NotBlank(message = "Reciever Id is required")
    private Long recieverId;

    @NotBlank(message = "Content is required")
    private String content;
}
