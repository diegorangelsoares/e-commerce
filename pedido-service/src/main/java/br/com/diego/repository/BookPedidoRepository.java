package br.com.diego.repository;

import br.com.diego.model.BookPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPedidoRepository extends JpaRepository<BookPedido, Long> {
}
