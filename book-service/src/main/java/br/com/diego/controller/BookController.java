package br.com.diego.controller;

import br.com.diego.model.Book;
import br.com.diego.reponse.Cambio;
import br.com.diego.proxy.CambioProxy;
import br.com.diego.request.BookRequest;
import br.com.diego.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
@Slf4j
public class BookController {

    private static String enderecoApiCambio = "http://localhost:8001";

    @Autowired
    private Environment environment;

    @Autowired
    private BookService bookService;

    @Autowired
    private CambioProxy cambioProxy;

//    @Operation(summary = "find a specific book by your ID")
//    @GetMapping (value = "/{id}/{currency}")
//    public ResponseEntity<Book> findBook(
//            @PathVariable("id") Long id,
//            @PathVariable("currency") String currency
//    ){
//        var port = environment.getProperty("local.server.port");
//        var book = bookService.getById(id);
//        if (book == null) throw new RuntimeException("Book not found.");
//
//        var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);
//
//        book.setEnvironment(port + " FEIGN");
//        book.setPrice(cambio.getConversionValue());
//        return ResponseEntity.status(HttpStatus.OK).body(book);
//    }

    @Operation(summary = "Save new book")
    @PostMapping(value = "/save")
    public ResponseEntity<Book> save(@Validated @RequestBody BookRequest bookRequest) {

        log.info("Salvando Book...");
        var port = environment.getProperty("local.server.port");
        if (bookRequest == null) throw new RuntimeException("Book incomplete.");

        if (bookRequest.getPrice() == null ) throw new RuntimeException("Price mandatory.");
        if (bookRequest.getAuthor() == null ) throw new RuntimeException("Author mandatory.");
        if (bookRequest.getTitle() == null ) throw new RuntimeException("Title mandatory.");

        bookRequest.setEnvironment(port + " FEIGN");

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookRequest));
    }


    @GetMapping (value = "/{id}/{currency}")
    public Book findBookPriceUpdate(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ){
        var port = environment.getProperty("local.server.port");
        var book = bookService.getById(id);
        if (book == null) throw new RuntimeException("Book not found.");

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from","USD");
        params.put("to", currency);

        var reponse = new RestTemplate().
                getForEntity(enderecoApiCambio+"/cambio-service/{amount}/{from}/{to}", Cambio.class, params);

        var cambio = reponse.getBody();

        book.setEnvironment(port);
        book.setPrice(cambio.getConversionValue());
        return book;
    }

}
