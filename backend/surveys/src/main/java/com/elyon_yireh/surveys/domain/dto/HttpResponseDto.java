package com.elyon_yireh.surveys.domain.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message", "code", "status", "data"})
public record HttpResponseDto<T>(String message, String code, String status, T data) {
}
