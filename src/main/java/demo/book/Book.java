package demo.book;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.util.Objects;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

@Entity
@IdClass(Book.Pk.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BookId id;

    @Convert(converter = BookNameConvertor.class)
    private BookName name;
    @Convert(converter = AuthorNameConvertor.class)
    private AuthorName author;

    protected Book() {}

    public Book(final BookId id, final BookName name, final AuthorName author) {
        this.id = requireNonNull(id);
        this.name = requireNonNull(name);
        this.author = requireNonNull(author);
    }

    public BookId getId() {
        return id;
    }

    public BookName getName() {
        return name;
    }

    public AuthorName getAuthor() {
        return author;
    }

    public <T> T map(final Function<Book, T> mapper) {
        requireNonNull(mapper);
        return mapper.apply(this);
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof final Book other
                && Objects.equals(id, other.id)
                && Objects.equals(name, other.name)
                && Objects.equals(author, other.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author);
    }

    @Override
    public String toString() {
        return "Book[id=%s, name=%s, author=%s]".formatted(id, name, author);
    }

    public static class Pk {
        @Column(name = "id")
        @Convert(converter = BookIdConvertor.class)
        private BookId id;
    }
}
