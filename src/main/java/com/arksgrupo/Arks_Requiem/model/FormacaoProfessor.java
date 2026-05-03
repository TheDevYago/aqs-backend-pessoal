package com.arksgrupo.Arks_Requiem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "formacaoProfessor")
public class FormacaoProfessor {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "categoriaTitulacao")
    private String titulacao;
    @Column(name = "instituicaoConclusao")
    private String instituicao;
    @Column(name = "anoConclusao")
    private int anoConclusao;
    @Column(name = "nomeCurso")
    private String nomeCurso;
    @ManyToOne
    @JoinColumn(name = "professorMatricula", referencedColumnName = "matricula")
    private Professor professor;

    public FormacaoProfessor(){}

    public Long getId() {
        return id;
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

    public int getAnoConclusao() {
        return anoConclusao;
    }
    public void setAnoConclusao(Integer anoConclusao) {
        this.anoConclusao = anoConclusao;
    }

    public Professor getProfessor() {
        return professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }


}
