package demo.book;

import java.io.Serial;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;

public record BookId(long value) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public BookId {
        checkArgument(value > 0, "Invalid book id");
    }
}
