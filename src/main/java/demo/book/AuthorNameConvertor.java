package demo.book;

import jakarta.persistence.AttributeConverter;

public class AuthorNameConvertor implements AttributeConverter<AuthorName, String> {

    @Override
    public String convertToDatabaseColumn(final AuthorName model) {
        return model == null ? null : model.value();
    }

    @Override
    public AuthorName convertToEntityAttribute(final String db) {
        return db == null ? null : new AuthorName(db);
    }
}
