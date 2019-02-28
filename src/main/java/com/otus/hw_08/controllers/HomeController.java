package com.otus.hw_08.controllers;

import com.otus.hw_08.services.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LibraryService libraryService;

    @GetMapping("/home")
    public String index(final Model model) {
        model.addAttribute("booksTotal", libraryService.getBooksCount());
        model.addAttribute("authorsTotal", libraryService.getAuthorsCount());
        return "index";
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }

}
