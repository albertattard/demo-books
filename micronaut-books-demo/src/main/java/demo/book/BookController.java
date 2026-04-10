package demo.book;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import static java.util.Objects.requireNonNull;

@Controller("/book")
public class BookController {

    private final BookService service;

    public BookController(final BookService service) {
        this.service = requireNonNull(service, "The book service cannot be null");
    }

    @Get("/random")
    public HttpResponse<BookTo> random() {
        return service.random()
                .map(BookTo::of)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }
}
