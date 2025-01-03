package com.elyon_yireh.surveys.security.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.ResponseCookie;

@JsonPropertyOrder({"username", "message", "cookie"})
public record AuthResponse(
        String username,
        String message,
        String cookie
) {
}