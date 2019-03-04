package com.otus.hw_08.repository.projections;

import com.otus.hw_08.controllers.LibraryController;
import org.springframework.ui.Model;

/**
 * This projection of book is to be used in search result
 * initiated by user in book search form.
 *
 * @see LibraryController#fullSearch(Model, String, String, String)
 */
public interface BookProjection {

    String getTitle();

    String getAuthors();

    String getGenres();

}
