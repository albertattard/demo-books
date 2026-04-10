package demo.book;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
class BookControllerTest {

    @Inject
    private BookService service;

    @Inject
    @Client("/")
    private HttpClient httpClient;

    @Test
    void returnNotFoundWhenNoQuotesAreFound() {
        /* Given */
        when(service.random()).thenReturn(Optional.empty());

        /* When */
        final AbstractThrowableAssert<?, ? extends Throwable> exception = assertThatThrownBy(this::makeGetRandomBookRequest);

        /* Then */
        exception.isInstanceOf(HttpClientResponseException.class)
                .describedAs("Return HTTP NOT_FOUND when a random book is not found");
    }

    @Test
    void returnFoundWithTheRandomQuote() {
        /* Given */
        final Book book = new Book(new BookId(1L), new BookName("Effective Java"), new AuthorName("Joshua Bloch"));
        when(service.random()).thenReturn(Optional.of(book));

        /* When */
        final HttpResponse<BookTo> response = makeGetRandomBookRequest();

        /* Then */
        assertThat(Optional.ofNullable(response.status()))
                .describedAs("Return HTTP OK when a random book is found")
                .isEqualTo(Optional.of(HttpStatus.OK));
        assertThat(response.body())
                .describedAs("Return the random book as a JSON object")
                .isEqualTo(BookTo.of(book));
    }

    private HttpResponse<BookTo> makeGetRandomBookRequest() {
        return httpClient.toBlocking().exchange("/book/random", BookTo.class);
    }

    @MockBean(BookServiceImpl.class)
    BookService mockBookService() {
        return mock(BookService.class);
    }
}
