package com.techment.OtrsSystem.controller;

import com.techment.OtrsSystem.domain.Category;
import com.techment.OtrsSystem.service.CategoryService;
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
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public CategoryService categoryService;

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Category> createCategory(@RequestBody @Validated CategoryDto categoryDto, @PathVariable("id") Long id,
                                             @RequestHeader(value = "Authorization") String token){
        LOGGER.info("authorised");
        return Optional.ofNullable(categoryService.createCategory(categoryDto.getCategoryName(), id, token));

    }

    @DeleteMapping("/delete/category/{categoryName}")
    @ResponseStatus(HttpStatus.CREATED)
    public String deleteCategory(@PathVariable("categoryName") String categoryName, @PathVariable("id") Long id,
                                 @RequestHeader(value = "Authorization") String token){
        LOGGER.info("authorised");
        return categoryService.deleteCategory(categoryName, id, token);

    }
}
