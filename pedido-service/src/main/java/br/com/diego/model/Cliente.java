package br.com.diego.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome", nullable = false, length = 180)
    private String nome;
    @Column(name = "cpf", nullable = false)
    private long cpf;

    @ManyToOne
    private Endereco endereco;



}