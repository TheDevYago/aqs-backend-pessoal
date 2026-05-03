package com.arksgrupo.Arks_Requiem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonitoriaDTO {

    private Long id;
    private Long alunoMatricula;
    private Long disciplinaId;
    private Long professorOrientadorMatricula;
    private String semestre;
    private String tipoMonitoria;
    private String localAtuacao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDateTime dataCadastro;
    

    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAlunoMatricula(Long alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Long getProfessorOrientadorMatricula() {
        return professorOrientadorMatricula;
    }

    public void setProfessorOrientadorMatricula(Long professorOrientadorMatricula) {
        this.professorOrientadorMatricula = professorOrientadorMatricula;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getTipoMonitoria() {
        return tipoMonitoria;
    }

    public void setTipoMonitoria(String tipoMonitoria) {
        this.tipoMonitoria = tipoMonitoria;
    }

    public String getLocalAtuacao() {
        return localAtuacao;
    }

    public void setLocalAtuacao(String localAtuacao) {
        this.localAtuacao = localAtuacao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

     public LocalDateTime getDataCadastro(){
        return dataCadastro;
    }
    public void setDataCadastro( LocalDateTime dataCadastro ){
        this.dataCadastro = dataCadastro;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
}