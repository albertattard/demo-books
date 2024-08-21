package demo.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookServiceTest {

    @MockBean
    private BookRepository repository;

    @Autowired
    private BookService service;

    @Test
    void returnOptionalEmptyWhenRepositoryReturnsNoBooks() {
        /* Given */
        when(repository.findAll()).thenReturn(List.of());

        /* When */
        final Optional<Book> response = service.random();

        /* Then */
        assertThat(response)
                .describedAs("The repository returned no books")
                .isEmpty();
    }

    @Test
    void returnTheSingleQuote() {
        /* Given */
        final Book book = BOOK_1;
        when(repository.findAll()).thenReturn(List.of(book));

        /* When */
        final Optional<Book> response = service.random();

        /* Then */
        assertThat(response)
                .describedAs("The single book returned by the repository")
                .isEqualTo(Optional.of(book));
    }

    @Test
    void returnARandomQuote() {
        /* Given */
        final List<Book> books = List.of(BOOK_1, BOOK_2);
        when(repository.findAll()).thenReturn(books);

        /* When */
        final Optional<Book> response = service.random();

        /* Then */
        assertThat(response)
                .describedAs("One of the books returned by the repository")
                .isPresent();
        assertThat(response.get())
                .describedAs("One of the books returned by the repository")
                .isIn(books);
    }

    private static final Book BOOK_1 = new Book(new BookId(1L), new BookName("Effective Java"), new AuthorName("Joshua Bloch"));

    private static final Book BOOK_2 = new Book(new BookId(2L), new BookName("Java Puzzlers: Traps, Pitfalls, and Corner Cases"), new AuthorName("Joshua Bloch/Neal Gafter"));
}
