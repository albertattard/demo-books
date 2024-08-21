package demo.book;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BookIdConvertor implements AttributeConverter<BookId, Long> {

    @Override
    public Long convertToDatabaseColumn(final BookId model) {
        return model == null ? null : model.value();
    }

    @Override
    public BookId convertToEntityAttribute(final Long db) {
        return db == null ? null : new BookId(db);
    }
}
