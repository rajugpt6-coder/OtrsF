package com.techment.OtrsSystem.controller;

import com.techment.OtrsSystem.domain.Department;
import com.techment.OtrsSystem.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/users/{id}")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public DepartmentService departmentService;

    @PostMapping("/department")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Department> createCategory(@RequestBody @Validated DepartmentDto departmentDto, @PathVariable("id") Long id,
                                               @RequestHeader(value = "Authorization") String token){
        LOGGER.info("authorised");
        return Optional.ofNullable(departmentService.createDepartment(departmentDto.getDepartmentName(), id, token));

    }

    @DeleteMapping("/delete/department/{departmentName}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCategory(@PathVariable("departmentName") String departmentName, @PathVariable("id") Long id,
                                               @RequestHeader(value = "Authorization") String token){
        LOGGER.info("authorised");
        return departmentService.deleteDepartment(departmentName, id, token);

    }
}
