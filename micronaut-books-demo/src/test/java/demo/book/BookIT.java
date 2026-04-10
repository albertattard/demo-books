package demo.book;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.netty.DefaultHttpClientBuilder;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class BookIT {

    @Test
    void fetchRandomQuote(final DefaultHttpClientBuilder httpClientBuilder) {
        try (final HttpClient restClient = httpClientBuilder
                .uri(baseUri())
                .build()) {

            final HttpResponse<BookTo> response = restClient.toBlocking().exchange("/book/random", BookTo.class);

            assertThat(response.code()).isEqualTo(200);

            final BookTo body = response.body();
            assertThat(body).isNotNull();
            assertThat(body.name()).isNotEmpty();
            assertThat(body.author()).isNotEmpty();
        }
    }

    private static URI baseUri() {
        final String serverPort = System.getProperty("test.server.port");
        assertThat(serverPort)
                .describedAs("Missing server port")
                .isNotEmpty();

        return URI.create("http://localhost:" + serverPort);
    }
}
