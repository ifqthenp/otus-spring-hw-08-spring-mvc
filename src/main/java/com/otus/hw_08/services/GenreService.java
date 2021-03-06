package com.otus.hw_08.services;

import com.otus.hw_08.domain.Genre;
import com.otus.hw_08.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Optional<Genre> findGenreByName(final String genreName) {
        return genreRepository.findByGenreNameContainingIgnoreCase(genreName);
    }

}
