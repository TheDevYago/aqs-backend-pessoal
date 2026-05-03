package com.arksgrupo.Arks_Requiem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
public class AlunoMonitor {

    @Id
    @Column(name= "matricula")
    private Long matricula;

    @Column(name = "nomecompleto", length = 150, nullable = false) //padronizado para minusculo conforme padtao do postgreSQL
    private String nomeCompleto;

    @Column(name = "datacadastro", updatable = false) //padronizado para minusculo conforme padtao do postgreSQL
    private LocalDateTime dataCadastro;

    private Boolean status = true;

    public AlunoMonitor() {}

    // Método para preencher a data de cadastro automaticamente antes de salvar no banco
    @PrePersist
    protected void onCreate() {
        if (dataCadastro == null) {
            dataCadastro = LocalDateTime.now();
        }
    }

    // Getters e Setters
    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}