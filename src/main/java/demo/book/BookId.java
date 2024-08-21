package demo.book;

import static com.google.common.base.Preconditions.checkArgument;

public record BookId(long value) {

    public BookId {
        checkArgument(value > 0, "Invalid book id");
    }
}
