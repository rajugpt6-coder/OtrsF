package com.techment.OtrsSystem.controller;

import javax.validation.constraints.NotNull;

public class CategoryDto {

    @NotNull
    private String categoryName;

    protected CategoryDto(){}

    public CategoryDto(@NotNull String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
