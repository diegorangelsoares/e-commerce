package br.com.diego.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClienteRequest {

    private String nome;
    private Date DataCriacao;

    EnderecoRequest endereco;

}
