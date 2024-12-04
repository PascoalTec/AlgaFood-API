package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
    
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;


    @Embedded // indica que esta propriedade é de um tipo "Embedado" incorporado pela classe Endereco
    private Endereco endereco;

    
    private Boolean ativo = Boolean.TRUE;


    @CreationTimestamp  // ele informa que a propriedade anotada, deve ser atribuida com a data hora atual
    @Column(nullable = false)
    private OffsetDateTime dataCadastro;


    @UpdateTimestamp    // Ela informa que a data hora atual deve ser atribuida sempre que a propriedade for atualizada
    @Column(nullable = false)
    private OffsetDateTime dataAtualizacao;


    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), // dando um Join em Restaurante
    inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")) // define o nome da forma pagamento
    private Set<FormaPagamento> formasPagamento = new HashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurante_usuario_responsavel", joinColumns = @JoinColumn(name = "restaurante_id"), 
    inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> responsaveis = new HashSet<>();

    public void ativar() {
        setAtivo(true);
    }

    public void inativar() {
        setAtivo(false);
    }

    public boolean desassociarFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().remove(formaPagamento);
    }

    public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().add(formaPagamento);
    }

    private Boolean aberto = Boolean.FALSE;

    public void abrir() {
        setAberto(true);
    }

    public void fechar() {
        setAberto(false);
    }

    public boolean adicionarResponsavel(Usuario usuario) {
        return getResponsaveis().add(usuario);
    }

    public boolean removerResponsavel(Usuario usuario) {
        return getResponsaveis().remove(usuario);
    }

    public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().contains(formaPagamento);
    }

    public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
        return !aceitaFormaPagamento(formaPagamento);
    }
}