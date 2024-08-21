package demo.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookService service;

    public BookController(final BookService service) {
        requireNonNull(service);
        this.service = service;
    }

    @GetMapping("/random")
    public ResponseEntity<BookTo> random() {
        return service.random()
                .map(BookTo::of)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
