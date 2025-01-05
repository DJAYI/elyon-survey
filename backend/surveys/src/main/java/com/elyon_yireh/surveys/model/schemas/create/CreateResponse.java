package com.elyon_yireh.surveys.model.schemas.create;

import com.elyon_yireh.surveys.model.schemas.ResponsesSchema;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"surveyId", "responses", "respondent"})

public record CreateResponse(
        @NotNull UUID surveyId,
        List<ResponsesSchema> responses,
        @NotNull CreateRespondent respondent
) {
}
