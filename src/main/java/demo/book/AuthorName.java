package demo.book;

import java.io.Serial;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;

public record AuthorName(String value) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public AuthorName {
        checkArgument(value != null && !value.isEmpty() && value.length() < 255);
    }
}
