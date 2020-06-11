package com.aiqrango.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ItemPedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal precoUnitario;
    BigDecimal precoTotal;
    Integer quantidade;
    String observacao;

    @ManyToOne
    @JoinColumn(nullable = false)
    Pedido pedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    Produto produto;

}
