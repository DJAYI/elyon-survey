package com.elyon_yireh.surveys.model.schemas.create;

import jakarta.validation.constraints.NotBlank;

public record CreateRespondent(
        @NotBlank String firstname,
        @NotBlank String lastname,
        @NotBlank String documentType,
        @NotBlank String documentNumber,
        @NotBlank String email,
        @NotBlank String phone,
        @NotBlank boolean student
) {
}
