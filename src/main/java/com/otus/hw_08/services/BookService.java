package com.otus.hw_08.services;

import com.otus.hw_08.repository.projections.BookProjection;
import com.otus.hw_08.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public long getCount() {
        return bookRepository.count();
    }

    public List<BookProjection> findBooksByRequestParameters(final String title, final String author, final String genre) {
        return bookRepository.findBooksByRequestParameters(title, author, genre);
    }

}
