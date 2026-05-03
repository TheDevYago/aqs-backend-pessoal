package com.arksgrupo.Arks_Requiem.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "matriz")
public class Matriz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String descricao;
    @Column(name = "dataCadastro")
    private LocalDateTime dataCadastro;
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "cursoId")
    private Curso curso;


    public Matriz() {}

    // Getters e Setters
    public Long getId() { 
        return id; 
    }
   
    public String getNome() { 
        return nome; 
    }
    public void setNome(String nome) { 
        this.nome = nome; 
    
    }

    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public LocalDateTime getDataCadastro(){
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public Curso getCurso() {
    return curso;
    }
    public void setCurso(Curso curso) {
    this.curso = curso;
    }
}