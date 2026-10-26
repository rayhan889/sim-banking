package com.rayhan889.simbanking.shared;

import java.util.UUID;

import org.springframework.security.oauth2.jwt.Jwt;

public record Actor(UUID userId, Role role) {

    public Actor {
        if (userId == null || role == null) {
            throw new IllegalArgumentException("userId and role are required");
        }
    }

    public static Actor from(Jwt jwt) {
        return new Actor(UUID.fromString(jwt.getSubject()), Role.valueOf(jwt.getClaimAsStringList("roles").getFirst()));
    }

    public boolean isTeller() {
        return role == Role.TELLER;
    }
}
