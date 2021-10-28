package br.com.diego.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter
@Setter
public class ClienteRequest {

    private String nome;
    private long cpf;

    private EnderecoRequest endereco;

}
