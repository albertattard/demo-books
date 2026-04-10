package demo.book;

import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Singleton
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(final BookRepository repository) {
        this.repository = requireNonNull(repository, "The book repository cannot be null");
    }

    @Override
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
