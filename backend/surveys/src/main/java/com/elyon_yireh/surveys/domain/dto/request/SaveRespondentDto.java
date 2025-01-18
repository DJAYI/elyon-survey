package com.elyon_yireh.surveys.domain.dto.request;


import com.elyon_yireh.surveys.utils.DocumentEnum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"firstname", "lastname", "email", "phone", "documentType", "documentNumber", "isStudent"})
public record SaveRespondentDto(@NotBlank String firstname, @NotBlank String lastname, @NotBlank String email,
                                @NotBlank String phone,
                                @NotBlank DocumentEnum documentType, @NotBlank String documentNumber,
                                @NotNull Boolean isStudent) {
}
