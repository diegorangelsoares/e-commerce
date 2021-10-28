package br.com.diego.service;

import br.com.diego.model.Endereco;
import br.com.diego.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco SalvarNovo(Endereco endereco){
        return enderecoRepository.save(endereco);
    }


}
