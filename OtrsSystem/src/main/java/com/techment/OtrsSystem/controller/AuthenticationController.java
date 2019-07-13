package com.techment.OtrsSystem.controller;

import com.techment.OtrsSystem.domain.User;
import com.techment.OtrsSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(@RequestBody @Validated UserDto userDto) {

        return userService.signup(userDto.getUsername(), userDto.getFirstName(),
                userDto.getMiddleName(),userDto.getLastName(), userDto.getEmployeeId(), userDto.getPhoneNumber(),
                userDto.getWorkingNumber(), userDto.getExtensionLandline(), userDto.getLandlineNumber(),
                userDto.getGender(), userDto.getDesignation(), userDto.getDepartment()).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST,"User already exists"));

    }


    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public String signin(@RequestBody @Validated UserDto userDto){
        return userService.signin(userDto.getUsername(), userDto.getPassword()).orElseThrow(()->
              new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed"));
    }

    @GetMapping("/forgotPassword/{email}")
    public String forgotPassword(@PathVariable("email") String email){
        return userService.forgotPassword(email);
    }

}
