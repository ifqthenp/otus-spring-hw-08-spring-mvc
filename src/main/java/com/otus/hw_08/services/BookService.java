package com.otus.hw_08.services;

import com.otus.hw_08.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public long getCount() {
        return bookRepository.count();
    }

}
