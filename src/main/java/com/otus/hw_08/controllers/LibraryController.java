package com.otus.hw_08.controllers;

import com.otus.hw_08.repository.projections.BookProjection;
import com.otus.hw_08.services.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/library/add")
    public String add() {
        return "add_book";
    }

    @GetMapping(value = "/library/books/search")
    public String findBook() {
        return "find_book";
    }

    @GetMapping(value = "/library/books/search/results")
    public String resultBook(final Model model,
                             @RequestParam(required = false) final String title,
                             @RequestParam(required = false) final String author,
                             @RequestParam(required = false) final String genre) {

        if (allParamsNotNull(title, author, genre) && allParamsNotBlank(title, author, genre)) {
            List<BookProjection> books = libraryService.findBooksByRequestParams(title, author, genre);
            model.addAttribute("books", books);
        }
        return "result_book";
    }

    private boolean allParamsNotBlank(final String title, final String author, final String genre) {
        return !title.isBlank() || !author.isBlank() || !genre.isBlank();
    }

    private boolean allParamsNotNull(final String title, final String author, final String genre) {
        return title != null && author != null && genre != null;
    }

}
