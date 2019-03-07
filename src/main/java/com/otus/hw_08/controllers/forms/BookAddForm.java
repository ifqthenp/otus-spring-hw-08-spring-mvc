package com.otus.hw_08.controllers.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class BookAddForm {

    @NotBlank
    private String title;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String genre;

    @NotBlank
    @Pattern(
        regexp = "\\d{4}",
        message = "Invalid year format. Must be 4 digits"
    )
    private String year;

}
