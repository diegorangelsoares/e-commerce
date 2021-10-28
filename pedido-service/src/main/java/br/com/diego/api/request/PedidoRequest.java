package br.com.diego.api.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PedidoRequest {

    private long id;

    private Date DataCriacao;

    private Date DataAlteracao;

    private Double totalTotal;

    private String status;

    private ClienteRequest cliente;

    private List<BookPedidoRequest> books;

}
