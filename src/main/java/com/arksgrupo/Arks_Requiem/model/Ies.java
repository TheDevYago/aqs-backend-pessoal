package com.arksgrupo.Arks_Requiem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ies")
public class Ies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean status;
    private String nome;
    private String endereco;
    private String telefone;

    public Ies() {
    }

    public Long getId() {
        return id;
    }
 
    public String getNome() {
        return nome;
    }

    public Boolean getStatus(){
        return status; 
    }

    public void setStatus(Boolean status){
        this.status = status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}