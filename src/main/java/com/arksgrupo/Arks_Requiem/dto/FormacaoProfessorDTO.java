package com.arksgrupo.Arks_Requiem.dto;

public class FormacaoProfessorDTO {
    private Long id;
    private String titulacao;
    private String instituicao;
    private Integer anoConclusao;
    private String nomeCurso;
    private Long professorMatricula;

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public Integer getAnoConclusao() {
        return anoConclusao;
    }

    public void setAnoConclusao(Integer anoConclusao) {
        this.anoConclusao = anoConclusao;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Long getProfessorMatricula() {
        return professorMatricula;
    }

    public void setProfessorMatricula(Long professorMatricula) {
        this.professorMatricula = professorMatricula;
    }
}
