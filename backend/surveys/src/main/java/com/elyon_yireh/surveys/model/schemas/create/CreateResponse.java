package com.elyon_yireh.surveys.model.schemas.create;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateResponse(
        @NotNull UUID surveyId,
        @NotNull Long questionId,
        @NotNull Long answerId,
        @NotNull CreateRespondent respondent
) {
}
