package com.otus.hw_08.services;

import com.otus.hw_08.controllers.forms.BookAddForm;
import com.otus.hw_08.domain.Author;
import com.otus.hw_08.domain.Book;
import com.otus.hw_08.domain.Genre;
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

    public Book saveBook(final Book book) {
        return bookRepository.save(book);

    }

    public Book saveFormAsBook(final BookAddForm bookForm) {
        final Author author = new Author();
        author.setFirstName(bookForm.getFirstName());
        author.setLastName(bookForm.getLastName());

        final Genre genre = new Genre();
        genre.setGenreName(bookForm.getGenre());

        final Book book = new Book();
        book.setTitle(bookForm.getTitle());
        book.setYear(bookForm.getYear());
        book.addAuthor(author);
        book.addGenre(genre);
        return bookRepository.saveAndFlush(book);
    }

}
