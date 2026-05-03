package com.arksgrupo.Arks_Requiem.dto;

import java.time.LocalDateTime;

public class CursoDTO {
    private Long id;
    private String sigla;
    private String descricao;
    private Long escolaId;
    private String turno;
    private String coordenadorCurso;
    private LocalDateTime dataCadastro;
    private Boolean status = true;
    private String nomeEscola;


    // GETTERS E SETTERS
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getEscolaId() {
        return escolaId;
    }

    public void setEscolaId(Long escolaId) {
        this.escolaId = escolaId;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCoordenadorCurso() {
        return coordenadorCurso;
    }
    public void setCoordenadorCurso(String coordenadorCurso) {
        this.coordenadorCurso = coordenadorCurso;
    }

    public LocalDateTime getDataCadastro(){
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro){
        this.dataCadastro = dataCadastro;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }
}
