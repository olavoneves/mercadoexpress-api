package br.com.fiap.mercadoexpress.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Representa um item disponivel no mercado express.
 * Mapeada na tabela TDS_TB_MERCADO do banco Oracle da FIAP.
 */
@Entity
@Table(name = "TDS_TB_MERCADO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mercado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMercado")
    @SequenceGenerator(name = "seqMercado", sequenceName = "TDS_SQ_MERCADO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "TIPO", nullable = false, length = 60)
    private String tipo;

    @Column(name = "SETOR", nullable = false, length = 60)
    private String setor;

    @Column(name = "TAMANHO", length = 30)
    private String tamanho;

    @Column(name = "PRECO", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
}
