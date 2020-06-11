package com.aiqrango.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal subtotal;
    BigDecimal taxaFrete;
    BigDecimal valorTotal;

    @Embedded
    Endereco enderecoEntrega;

    StatusPedido status;

    @CreationTimestamp
    LocalDateTime dataCriacao;

    LocalDateTime dataConfirmacao;
    LocalDateTime dataCancelamento;
    LocalDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(nullable = false)
    FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(nullable = false)
    Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    Usuario cliente;

    @OneToMany(mappedBy = "pedido")
    List<ItemPedido> itens = new ArrayList<>();

}
