package com.tenpo.math.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignupUserRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private List<Role> roles;

    private boolean active;
}
