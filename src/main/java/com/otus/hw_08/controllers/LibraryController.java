package com.otus.hw_08.controllers;

import com.otus.hw_08.repository.projections.BookProjection;
import com.otus.hw_08.services.BookService;
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

    private final BookService bookService;

    @GetMapping("/library/add")
    public String add() {
        return "book_add_new";
    }

    @GetMapping(value = "/library/books/search")
    public String bookSearchForm() {
        return "book_search_form";
    }

    @GetMapping(value = "/library/books/search/full")
    public String fullSearch(final Model model,
                             @RequestParam(required = false) final String title,
                             @RequestParam(required = false) final String author,
                             @RequestParam(required = false) final String genre) {

        if (allParamsNotNull(title, author, genre) && allParamsNotBlank(title, author, genre)) {
            List<BookProjection> books = bookService.findBooksByRequestParameters(title, author, genre);
            model.addAttribute("books", books);
        }
        return "book_search_result";
    }

    @GetMapping(value = "/library/books/search/quick")
    public String quickSearch(@RequestParam(required = false) String title, final Model model) {
        if (!title.isBlank()) {
            List<BookProjection> books = bookService.findBooksByRequestParameters(title, "", "");
            model.addAttribute("books", books);
        }
        return "book_search_result";
    }

    private boolean allParamsNotBlank(final String title, final String author, final String genre) {
        return !title.isBlank() || !author.isBlank() || !genre.isBlank();
    }

    private boolean allParamsNotNull(final String title, final String author, final String genre) {
        return title != null && author != null && genre != null;
    }

}
