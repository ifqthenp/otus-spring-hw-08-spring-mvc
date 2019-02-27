package com.otus.hw_08.dao

import com.otus.hw_08.domain.Book
import com.otus.hw_08.domain.Comment
import com.otus.hw_08.repository.AuthorRepository
import com.otus.hw_08.repository.BookRepository
import com.otus.hw_08.repository.CommentRepository
import com.otus.hw_08.repository.GenreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification
import spock.lang.Subject

@DataJpaTest
class BookRepositorySpec extends Specification {

    @Subject
    @Autowired
    BookRepository bookRepo

    @Autowired
    AuthorRepository authorRepo

    @Autowired
    CommentRepository commentRepo

    @Autowired
    GenreRepository genreRepo

    void setup() {
        assert bookRepo != null
        assert genreRepo != null
        assert authorRepo != null
        assert commentRepo != null
    }

    def "can find book by id"() {
        given:
        def bookId = 2L

        and:
        Optional<Book> book = bookRepo.findById(bookId)

        expect:
        book.isPresent()
        with(book.get()) {
            id == bookId
            title == 'Jane Eyre'
            year == '1847'
        }
    }

    def "can find books by title, case insensitive"() {
        given:
        def books = bookRepo.findByTitleContainingIgnoreCase(title)

        expect:
        books.size() == sizeRes
        books.every {
            it.title.toLowerCase().contains(title)
        }

        where:
        title   || sizeRes
        'child' || 1
        'time'  || 2
    }

    def "can find all books"() {
        given:
        Iterable<Book> books = bookRepo.findAll()

        expect:
        books.size() == 12
        with(books.last()) {
            id == 12
            title == 'The Little Golden Calf'
            year == '1931'
            genres.genreName == ['Satire', 'Novel']
            authors.lastName == ['Petrov', 'Ilf']
        }
    }

    def "can count books in the table"() {
        expect:
        bookRepo.count() == 12
    }

    @DirtiesContext
    def "can save a book"() {
        given:
        def t = 'Test book'
        def y = '2000'
        Book book = new Book(title: t, year: y)

        when:
        book = bookRepo.save(book)
        def allBooks = bookRepo.findAll()

        then:
        with(book) {
            id == allBooks.size()
            title == t
            year == y
            comments == []
            authors == [] as Set
        }

        and:
        allBooks.size() == 13
    }

    @DirtiesContext
    def "can save a collection of books"() {
        given:
        Iterable<Book> allBooks = bookRepo.findAll()

        and:
        def newBooks = [['War and Peace', '1869'], ['The Invisible Man', '1933']].collect {
            new Book(title: it[0], year: it[1])
        }

        when:
        newBooks = bookRepo.saveAll(newBooks)

        and:
        allBooks = bookRepo.findAll()

        then:
        allBooks.size() == old(allBooks.size()) + newBooks.size()
        newBooks.each { it.id > allBooks.size() }
        allBooks.last().title == 'The Invisible Man'
    }

    @DirtiesContext
    def "can delete a book"() {
        given:
        def bookId = 3L
        Optional<Book> book = bookRepo.findById(bookId)

        when:
        book.ifPresent({ bookRepo.delete(book.get()) })

        then:
        bookRepo.findById(bookId) == Optional.empty()
    }

    def "can find books by author"() {
        given:
        def leo = 'leo'
        def emptyTest = 'test'

        when:
        Iterable<Book> leoBooks = bookRepo.findBookByAuthorName(leo)
        Iterable<Book> emptyBooks = bookRepo.findBookByAuthorName(emptyTest)

        then:
        emptyBooks.isEmpty()
        leoBooks.size() == 4
        leoBooks.first().title == 'Anna Karenina'
    }

    def "can find books by genre, case insensitive"() {
        when:
        def books = bookRepo.findBookByGenreName(genre)

        then:
        books.size() == sizeResult
        if (sizeResult > 0) books.first().title == titleResult

        where:
        genre     || sizeResult || titleResult
        'realism' || 3          || 'Anna Karenina'
        'fant'    || 1          || 'Alice in Wonderland'
        'none'    || 0          || _
    }

    @DirtiesContext
    def "can add a comment to a book"() {
        given:
        def bookId = 1L
        def text = 'The best book ever'
        def comments = commentRepo.findAll()
        Book book = bookRepo.findById(bookId).get()
        Comment comment = new Comment(commentary: text)

        when:
        book.addComment(comment)
        bookRepo.save(book)

        and:
        comments = commentRepo.findAll()

        then:
        comments.size() == old(comments.size()) + 1
        bookRepo.findById(bookId).get().comments.last().commentary == text
    }

    @DirtiesContext
    def "can delete a comment from a book"() {
        given:
        def bookId = 2L
        def aBook = bookRepo.findById(bookId).get()
        def uselessComment = aBook.comments.first()
        def allCommentsInDatabase = commentRepo.findAll()

        when:
        aBook.removeComment(uselessComment)
        aBook = bookRepo.save(aBook)

        and:
        allCommentsInDatabase = commentRepo.findAll()

        then:
        aBook.comments.size() == old(aBook.comments.size()) - 1
        allCommentsInDatabase.size() == old(allCommentsInDatabase.size()) - 1
    }

    void cleanup() {
        bookRepo = null
        genreRepo = null
        authorRepo = null
        commentRepo = null
    }
}
