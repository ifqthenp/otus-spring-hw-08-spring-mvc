package com.otus.hw_08.services;

import com.otus.hw_08.repository.projections.BookProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<BookProjection> findBooksByRequestParams(final String title, final String author, final String genre) {
        return bookService.findBooksByRequestParameters(title, author, genre);
    }

}
