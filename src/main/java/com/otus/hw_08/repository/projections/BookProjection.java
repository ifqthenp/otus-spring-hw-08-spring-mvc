package com.otus.hw_08.repository.projections;

/**
 * This projection of book is to be used in search result
 * initiated by user in book search form.
 */
public interface BookProjection {

    String getTitle();

    String getAuthors();

    String getGenres();

}
