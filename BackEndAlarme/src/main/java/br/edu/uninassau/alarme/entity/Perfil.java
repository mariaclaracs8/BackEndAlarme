package br.edu.uninassau.alarme.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "cor_avatar")
    private String corAvatar;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    @JsonIgnore
    private Usuario usuario;

    public Perfil() {
    }

    public Perfil(Long id, String nome, String corAvatar, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.corAvatar = corAvatar;
        this.usuario = usuario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCorAvatar() { return corAvatar; }
    public void setCorAvatar(String corAvatar) { this.corAvatar = corAvatar; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}