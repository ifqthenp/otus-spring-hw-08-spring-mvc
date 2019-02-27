package com.otus.hw_08.repository;

import com.otus.hw_08.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE " +
        "LOWER(CONCAT(TRIM(a.firstName), ' ' , TRIM(a.lastName))) LIKE " +
        "LOWER(CONCAT('%', TRIM(:name), '%'))")
    Iterable<Book> findBookByAuthorName(@Param("name") String name);

    @Query("SELECT b FROM Book b JOIN b.genres g WHERE " +
        "LOWER(g.genreName) LIKE LOWER(CONCAT('%', TRIM(:gName), '%'))")
    Iterable<Book> findBookByGenreName(@Param("gName") String genreName);

    Iterable<Book> findByTitleContainingIgnoreCase(String title);



}
