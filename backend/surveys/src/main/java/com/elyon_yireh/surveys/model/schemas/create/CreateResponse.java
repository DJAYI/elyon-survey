package com.elyon_yireh.surveys.model.schemas.create;

import jakarta.validation.constraints.NotNull;

public record CreateResponse(
        @NotNull int answerId,
        @NotNull String respondentDoc
) {
}
