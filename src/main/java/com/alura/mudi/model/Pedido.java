package com.alura.mudi.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.alura.mudi.enuns.StatusPedido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nomeProduto;

    private String urlProduto;

    private String urlImagem;

    private String descricao;

    private Double valorNegociado;

    private LocalDate dataDaEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<Oferta> ofertas;
}
