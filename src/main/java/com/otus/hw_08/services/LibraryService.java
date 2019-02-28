package com.otus.hw_08.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LibraryService {

    private final BookService bookService;
    private final AuthorService authorService;

    public long getAuthorsCount() {
        return authorService.getCount();
    }

    public long getBooksCount() {
        return bookService.getCount();
    }

}
