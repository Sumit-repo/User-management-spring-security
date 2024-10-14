package com.epam.user_management.dto.requestDto;

import com.epam.user_management.entity.Role;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserRequestDto {
    private String username;
    private String password;
}
