package demo.book;

import io.micronaut.serde.annotation.Serdeable;

import static java.util.Objects.requireNonNull;

@Serdeable
public record BookTo(String name, String author) {

    public static BookTo of(final Book book) {
        requireNonNull(book, "The book cannot be null.");

        return new BookTo(
                book.getName().value(),
                book.getAuthor().value());
    }
}
