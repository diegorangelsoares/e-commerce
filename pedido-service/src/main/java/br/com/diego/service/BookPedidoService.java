package br.com.diego.service;

import br.com.diego.model.BookPedido;
import br.com.diego.repository.BookPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookPedidoService {

    @Autowired
    private BookPedidoRepository bookPedidoRepository;

    private BookPedidoService (BookPedidoRepository bookPedidoRepository){
        this.bookPedidoRepository = bookPedidoRepository;
    }

    public BookPedido salvar (BookPedido bookPedido){
        return bookPedidoRepository.save(bookPedido);
    }

    public List<BookPedido> saveAll (List<BookPedido> booksPedido){
        return bookPedidoRepository.saveAll(booksPedido);
    }

}
