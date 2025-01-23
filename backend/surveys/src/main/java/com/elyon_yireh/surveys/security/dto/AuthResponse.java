package com.elyon_yireh.surveys.security.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "status", "data"})
public record AuthResponse(
        String username,
        String message,
        String status,
        String data
) {
}