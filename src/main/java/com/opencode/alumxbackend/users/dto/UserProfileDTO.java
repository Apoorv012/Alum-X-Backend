package com.opencode.alumxbackend.users.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {
        private Long id;
        private String username;
        private String name;
        private String email;
        List<String> skills;
        List<String> education;
        List<String> techStack;
}
