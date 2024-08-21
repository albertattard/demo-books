package demo.book;

import static com.google.common.base.Preconditions.checkArgument;

public record BookName(String value) {

    public BookName {
        checkArgument(value != null && !value.isEmpty() && value.length() < 255);
    }
}
