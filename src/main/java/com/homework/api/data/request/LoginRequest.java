package com.homework.api.data.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotNull
    @Valid
    @Size(min = 8, max = 128, message = "A maximum of 128 characters can be entered.")
    private String email;

    @NotNull
    @Valid
    @Size(min = 4, max = 32, message = "A maximum of 32 characters can be entered.")
    private String password;

}
