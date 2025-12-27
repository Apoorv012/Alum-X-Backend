package com.opencode.alumxbackend.groupchat.repository;

import com.opencode.alumxbackend.groupchat.model.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupChatRepository extends JpaRepository<GroupChat, String> {}