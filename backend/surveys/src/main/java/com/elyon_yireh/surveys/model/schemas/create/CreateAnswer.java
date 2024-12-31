package com.elyon_yireh.surveys.model.schemas.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAnswer(
        @NotBlank String statement,
        @NotNull int value
) {
}
