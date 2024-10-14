package com.epam.user_management.entity;

import lombok.Getter;

@Getter
public enum Role {
    MOD(1, "Moderator"),
    USER(2, "User"),
    ADMIN(3, "Administrator");

    private final Integer id;
    private final String authority;

    Role(Integer id, String authority) {
        this.id = id;
        this.authority = authority;
    }
}
