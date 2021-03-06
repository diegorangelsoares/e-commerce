package br.com.diego.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoResponseApiCep {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

}
