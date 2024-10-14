package com.epam.user_management.dto.responseDto;

import com.epam.user_management.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserResponseDto {
    private Integer userId;
    private String username;
    private String password;
    private List<String> roles;
}
