package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.algaworks.algafood.core.validation.Groups;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
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
    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;


    
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;


    @Embedded // indica que esta propriedade é de um tipo "Embedado" incorporado pela classe Endereco
    private Endereco endereco;


    @CreationTimestamp  // ele informa que a propriedade anotada, deve ser atribuida com a data hora atual
    @Column(nullable = false)
    private OffsetDateTime dataCadastro;


    @UpdateTimestamp    // Ela informa que a data hora atual deve ser atribuida sempre que a propriedade for atualizada
    @Column(nullable = false)
    private OffsetDateTime dataAtualizacao;


    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), // dando um Join em Restaurante
    inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")) // define o nome da forma pagamento
    private List<FormaPagamento> formasPagamento = new ArrayList<>();



    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

}
