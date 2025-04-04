package demo.book;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

public class BookIT {

    @Test
    void fetchRandomQuote() {
        final RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl())
                .build();

        final BookTo body = restClient.get()
                .uri("/book/random")
                .retrieve()
                .body(BookTo.class);

        assertThat(body).isNotNull();
        assertThat(body.name()).isNotEmpty();
        assertThat(body.author()).isNotEmpty();
    }

    private static String baseUrl() {
        final String serverPort = System.getProperty("test.server.port");
        assertThat(serverPort)
                .describedAs("Missing server port")
                .isNotEmpty();

        return "http://localhost:".concat(serverPort);
    }
}
