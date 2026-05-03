package com.arksgrupo.Arks_Requiem.dto;

import java.time.LocalDateTime;

public class EscolaDTO {
    private Long id;
    private String nome;
    private Long coordenadorId;
    private Long iesId;
    private LocalDateTime dataCadastro;
    private boolean status;
    private String nomeCoordenador;
    private String nomeIes;

    public EscolaDTO(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCoordenadorId() {

        return coordenadorId;

    }

    public void setCoordenadorId(Long coordenadorId) {

        this.coordenadorId = coordenadorId;

    }


    public Long getIesId() {

        return iesId;

    }

    public void setIesId(Long iesId) {

        this.iesId = iesId;

    }

    public LocalDateTime getDataCadastro() {

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

    public String getNomeCoordenador() {
        return nomeCoordenador;
    }

    public void setNomeCoordenador(String nomeCoordenador) {
        this.nomeCoordenador = nomeCoordenador;
    }

    public String getNomeIes() {
        return nomeIes;
    }

    public void setNomeIes(String nomeIes) {
        this.nomeIes = nomeIes;
    }
    
}
