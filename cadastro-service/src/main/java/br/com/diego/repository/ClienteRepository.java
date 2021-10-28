package br.com.diego.repository;

import br.com.diego.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //Cliente findById(long id);

}
