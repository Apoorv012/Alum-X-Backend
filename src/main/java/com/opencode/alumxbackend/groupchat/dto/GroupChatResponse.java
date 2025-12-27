package com.opencode.alumxbackend.groupchat.dto;

import com.opencode.alumxbackend.groupchat.model.GroupChat.Participant;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatResponse {
    private String groupId;
    private String name;
    private List<Participant> participants;
}
