package com.elyon_yireh.surveys.domain.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message", "code", "status", "data"})
public record HttpResponseListDto<T>(String message, String code, String status, Iterable<T> data) {
}
