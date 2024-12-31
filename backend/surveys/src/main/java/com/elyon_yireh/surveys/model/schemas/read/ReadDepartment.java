package com.elyon_yireh.surveys.model.schemas.read;

import jakarta.validation.constraints.NotBlank;

public record ReadDepartment(
        @NotBlank String code,
        @NotBlank String name
) {
}
