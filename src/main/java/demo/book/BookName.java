package demo.book;

import java.io.Serial;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;

public record BookName(String value) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public BookName {
        checkArgument(value != null && !value.isEmpty() && value.length() < 255);
    }
}
