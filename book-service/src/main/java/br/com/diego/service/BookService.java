package br.com.diego.service;

import br.com.diego.exception.BookNotFoundException;
import br.com.diego.model.Book;
import br.com.diego.repository.BookRepository;
import br.com.diego.request.BookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format(
                        "Book not found by ID [%s]!", id)));
    }

    public Book save (BookRequest bookRequest){
        Book book = new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setCurrency(bookRequest.getCurrency());
        book.setPrice(bookRequest.getPrice());
        book.setEnvironment(bookRequest.getEnvironment());
        book.setLaunchDate(bookRequest.getLaunchDate());
        book.setTitle(bookRequest.getTitle());
        bookRepository.save(book);
        return book;
    }

}
