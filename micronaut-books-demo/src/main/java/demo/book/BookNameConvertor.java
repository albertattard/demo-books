package demo.book;

import jakarta.persistence.AttributeConverter;

public class BookNameConvertor implements AttributeConverter<BookName, String> {

    @Override
    public String convertToDatabaseColumn(final BookName model) {
        return model == null ? null : model.value();
    }

    @Override
    public BookName convertToEntityAttribute(final String db) {
        return db == null ? null : new BookName(db);
    }
}
