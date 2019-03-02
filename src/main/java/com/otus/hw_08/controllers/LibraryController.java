package com.otus.hw_08.controllers;

import com.otus.hw_08.services.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/library/add")
    public String add() {
        return "add_book";
    }

    @GetMapping("/library/books/search")
    public String findBook(@RequestParam(value = "title", required = false) final String title,
                           @RequestParam(value = "author", required = false) final String author,
                           @RequestParam(value = "genre", required = false) final String genre) {
        return "find_book";
    }

}
