package br.com.diego.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class BookPedidoRequest {

    private int count;
    private String nome;
    private Double price;

//    @ManyToOne
//    private Pedido pedido;


}
