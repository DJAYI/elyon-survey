package com.elyon_yireh.surveys.model.schemas.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSurvey(
        @NotNull int departmentId
) {
}
