package com.techment.OtrsSystem.controller;

import javax.validation.constraints.NotNull;

public class LoginDto {
    @NotNull
    private String username;

    @NotNull
    private String password;

    public LoginDto(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
