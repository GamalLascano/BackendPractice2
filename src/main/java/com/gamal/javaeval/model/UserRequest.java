package com.gamal.javaeval.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class UserRequest {
    private String name;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
    message = "Must be a well-formatted email")
    @NotNull
    private String email;
    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[\\d].*[\\d])[a-zA-Z\\d]{8,12})$",
            message = "Password should be 8-12 characters long, including an uppercase letter and two numbers")
    @NotNull
    private String password;

    private List<Phones> phones;
}
