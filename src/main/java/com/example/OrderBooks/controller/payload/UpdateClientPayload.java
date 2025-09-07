package com.example.OrderBooks.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateClientPayload(
        @NotNull(message = "{library.client.update.errors.full_name_is_null}")
        @Size(min = 3, max = 255, message = "{library.update.create.errors.full_name_size_is_invalid}")
        String fullName,
        @NotNull(message = "{library.update.create.errors.birth_date_is_null}")
        @Past(message = "{library.update.create.errors.birth_date_is_invalid}")
        LocalDate birthDate
) {
}
