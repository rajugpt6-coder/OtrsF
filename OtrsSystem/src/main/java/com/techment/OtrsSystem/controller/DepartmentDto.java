package com.techment.OtrsSystem.controller;

import javax.validation.constraints.NotNull;

public class DepartmentDto {
    @NotNull
    private String departmentName;

    protected DepartmentDto(){}

    public DepartmentDto(@NotNull String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
