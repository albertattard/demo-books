package demo.book;

import static com.google.common.base.Preconditions.checkArgument;

public record AuthorName(String value) {

    public AuthorName {
        checkArgument(value != null && !value.isEmpty() && value.length() < 255);
    }
}
