package br.edu.uninassau.alarme.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "medicamento")
@Schema(description = "Informações sobre o remédio a ser tomado")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do medicamento", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "unidade_medida")
    private String unidadeMedida;

    private String descricao;

    public Medicamento() {}

    public Medicamento(Long id, String nome, String unidadeMedida, String descricao) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}