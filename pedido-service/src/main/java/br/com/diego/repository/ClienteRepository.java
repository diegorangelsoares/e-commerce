package br.com.diego.repository;

import br.com.diego.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Override
    Optional<Cliente> findById(Long aLong);

    //Cliente findByCpf(long cpf);

    List<Cliente> findByCpf(long cpf);


}
