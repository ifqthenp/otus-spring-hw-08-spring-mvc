package com.otus.hw_08.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {

    @GetMapping("/library/add")
    public String add() {
        return "add_book";
    }

    @GetMapping("/library/books/search")
    public String find() {
        return "find_book";
    }

}
