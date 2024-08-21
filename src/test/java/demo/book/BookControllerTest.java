package demo.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @MockBean
    private BookService service;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate.getRestTemplate()
                .setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    void returnNotFoundWhenNoQuotesAreFound() {
        /* Given */
        when(service.random()).thenReturn(Optional.empty());

        /* When */
        final ResponseEntity<BookTo> response = makeGetRandomBookRequest();

        /* Then */
        assertThat(response.getStatusCode())
                .describedAs("Return HTTP NOT_FOUND when a random book is not found")
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void returnFoundWithTheRandomQuote() {
        /* Given */
        final Book book = new Book(new BookId(1L), new BookName("Effective Java"), new AuthorName("Joshua Bloch"));
        when(service.random()).thenReturn(Optional.of(book));

        /* When */
        final ResponseEntity<BookTo> response = makeGetRandomBookRequest();

        /* Then */
        assertThat(response.getStatusCode())
                .describedAs("Return HTTP OK when a random book is found")
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .describedAs("Return the random book as a JSON object")
                .isEqualTo(BookTo.of(book));
    }

    private ResponseEntity<BookTo> makeGetRandomBookRequest() {
        return restTemplate.getForEntity("/book/random", BookTo.class);
    }
}
