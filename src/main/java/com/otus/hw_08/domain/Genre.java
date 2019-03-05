package com.otus.hw_08.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NaturalId
    @Column(name = "genre")
    private String genreName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "genres")
    private final Set<Book> books = new HashSet<>();

//    @PreRemove
//    public void removeBooks() {
//        books.forEach(book -> book.removeGenre(this));
//    }

}
