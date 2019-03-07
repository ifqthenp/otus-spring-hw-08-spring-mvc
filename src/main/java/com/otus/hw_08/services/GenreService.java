package com.otus.hw_08.services;

import com.otus.hw_08.domain.Genre;
import com.otus.hw_08.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Optional<Genre> findGenreByName(final String genreName) {
        return genreRepository.findByGenreNameContainingIgnoreCase(genreName);
    }

}
