package demo.book;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public record BookTo(String name, String author) {

    public static BookTo of(final Book book) {
        requireNonNull(book, "The book cannot be null.");

        return new BookTo(
                book.getName().value(),
                book.getAuthor().value());
    }

    public <T> T map(final Function<BookTo, T> mapper) {
        requireNonNull(mapper, "The mapper cannot be null");
        return mapper.apply(this);
    }
}
