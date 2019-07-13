package com.techment.OtrsSystem.controller;

import javax.validation.constraints.NotNull;

public class TicketDto {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String category;

    private String status;

    public TicketDto(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }
}
