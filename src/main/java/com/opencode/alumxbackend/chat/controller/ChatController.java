package com.opencode.alumxbackend.chat.controller;

import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.opencode.alumxbackend.chat.dto.ChatSendRequest;
import com.opencode.alumxbackend.chat.dto.ChatSendResponse;
import com.opencode.alumxbackend.common.exception.Errors.UnauthorizedAccessException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private static final String DUMMY_TOKEN = "alumx-dev-token";
    private static final Logger logger = Logger.getLogger(ChatController.class.getName());

    @PostMapping("/send")
    public ResponseEntity<ChatSendResponse> sendMessage(
            @RequestHeader(value = "X-DUMMY-TOKEN", required = false) String token,
            @Valid @RequestBody ChatSendRequest request) {

       if (token == null || !token.equals(DUMMY_TOKEN)) {
           logger.warning("Unauthorized access attempt to Dev API. Missing or invalid token.");
           throw new UnauthorizedAccessException("Invalid or missing X-DUMMY-TOKEN header");
       }

        return null;

    }

}
