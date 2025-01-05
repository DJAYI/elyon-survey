package com.elyon_yireh.surveys.model.schemas;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
@JsonPropertyOrder({"questionId", "answerId"})
public record ResponsesSchema(
        Long questionId,
        Long answerId
) {
}
