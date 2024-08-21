package demo.book;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public record BookTo(String name, String author) {

    public static BookTo of(final Book book) {
        requireNonNull(book);

        return new BookTo(
                book.getName().value(),
                book.getAuthor().value());
    }

    public <T> T map(final Function<BookTo, T> mapper) {
        requireNonNull(mapper);
        return mapper.apply(this);
    }
}
