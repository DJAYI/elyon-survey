package com.elyon_yireh.surveys.model.schemas.create;

import jakarta.validation.constraints.NotBlank;

public record CreateQuestion(
            @NotBlank String statement
) {
}
