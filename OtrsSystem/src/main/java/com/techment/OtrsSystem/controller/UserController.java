package com.techment.OtrsSystem.controller;

import com.techment.OtrsSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("{id}/updatePassword/oldPassword/{oldPassword}/newPassword/{newPassword}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR') or hasRole('ROLE_USER')")
    public String updatePassword(@PathVariable("id") long id, @PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword,
                                 @RequestHeader("Authorization") String token){
        return userService.updatePassword(id, oldPassword, newPassword, token);
    }
}
