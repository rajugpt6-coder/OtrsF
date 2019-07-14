package com.techment.OtrsSystem.controller;

import com.techment.OtrsSystem.domain.User;
import com.techment.OtrsSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("{id}/updatePassword/oldPassword/{oldPassword}/newPassword/{newPassword}")
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR') or hasRole('ROLE_USER')")
    public String updatePassword(@PathVariable("id") long id, @PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword,
                                 @RequestHeader("Authorization") String token){
        return userService.updatePassword(id, oldPassword, newPassword, token);
    }

    @PatchMapping("{id}/updateProfile")
    @ResponseStatus(HttpStatus.OK)
    public void updatePhoneNumber(@PathVariable("id") long id, @RequestBody UserDto userDto,
                                  @RequestHeader("Authorization") String token) {
        userService.updateProfile(id, userDto.getPhoneNumber(), userDto.getLandlineNumber(),
                userDto.getWorkingNumber(), userDto.getExtensionLandline(), userDto.getDesignation(), token);
    }

    @PatchMapping("{id}/deactivate/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deactivateUser(@PathVariable("userId") long userId) {
        userService.deactivateUser(userId);
    }

    @PatchMapping("{id}/activate/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void activateUser(@PathVariable("userId") long userId) {
        userService.activateUser(userId);
    }

    @PatchMapping("{id}/addFeatureAccess/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void addFeatureAccess(@PathVariable("userId") long userId, @RequestBody UserDto userDto){
        userService.setFeaturesAccess(userId, userDto.getFeatureAccessList());
    }

    @PatchMapping("removeFeatures/{userId}")
    public void removeFeatures(@PathVariable("userId") long userId, @RequestBody UserDto userDto){
            userService.removeFeatureAccess(userId, userDto.getFeatureAccessList());
    }

    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR')")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAll(pageable);
    }


    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Optional<User> getUserDetails(@PathVariable("id") long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/myDetails/{email}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR') or hasRole('ROLE_USER')")
    public User getMyDetails(@PathVariable("email") String email, @RequestHeader(value="Authorization") String token ) {
        return userService.findUserByEmail(email, token);
    }

    //
    @GetMapping("/search/employeeId/{employeeId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<User> getUsersByEmployeeId(@PathVariable("employeeId") String employeeId, Pageable pageable) {
        return userService.findUsersByEmployeeId(employeeId, pageable);
    }

    @GetMapping("/search/firstName/{firstName}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<User> getUsersByFirstName(@PathVariable("firstName") String firstName, Pageable pageable) {
        return userService.findUsersByFirstName(firstName, pageable);
    }

    @GetMapping("/search/activationStatus/{flag}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<User> getUsersByActivationStatus(@PathVariable("flag") boolean flag, Pageable pageable) {
        return userService.findUsersByFlag(flag, pageable);
    }

    @GetMapping("/search/email/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/search/phoneNumber/{phoneNumber}")
    public Page<User> getUsersByPhone(@PathVariable("phoneNumber") String phoneNumber, Pageable pageable) {
        return userService.findByPhoneNumber(phoneNumber, pageable);
    }

    @GetMapping("/search/department/{department}")
    public Page<User> getUsersByDepartment(@PathVariable("department") String department, Pageable pageable){
        return userService.findByDepartment(department, pageable);
    }

    @GetMapping("/search/designation/{designation}")
    public Page<User> getUsersByDesignation(@PathVariable("designation") String designation, Pageable pageable) {
        return userService.findByDesignation(designation, pageable);
    }

}
