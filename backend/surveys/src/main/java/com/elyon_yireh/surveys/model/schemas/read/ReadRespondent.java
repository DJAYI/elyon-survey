package com.elyon_yireh.surveys.model.schemas.read;

import jakarta.validation.constraints.NotBlank;

public record ReadRespondent(
        @NotBlank String documentType,
        @NotBlank String documentNumber,
        @NotBlank String email,
        @NotBlank boolean student
) {
}
