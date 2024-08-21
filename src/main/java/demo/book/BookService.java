package demo.book;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(final BookRepository repository) {
        this.repository = requireNonNull(repository);
    }

    public Optional<Book> random() {
        /* Returns a random book, inefficiently, by loading all the books from
            the database and then picking one at random. The table contains more
            than 30 thousand books, which are all read and serialised
            unnecessarily. A more efficient way would have fetched the count of
            books, then return the one found at a random index using the SQL
            LIMIT and OFFSET. */
        final List<Book> books = new ArrayList<>(repository.findAll());

        if (books.isEmpty()) {
            return Optional.empty();
        }

        Collections.shuffle(books);
        return Optional.of(books.getFirst());
    }
}
