package com.arksgrupo.Arks_Requiem.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "monitoria") //padronizado para minusculo conforme padrao do postgreSQL
public class Monitoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "alunoMatricula") //trocado @Column por @JoinColumn e padronizado minúsculo
    private AlunoMonitor matriculaAluno;

    @Column(name = "tipomonitoria") //padronizado para minusculo conforme padrao do postgreSQLq
    private String tipoMonitoria; 

    @Column(name = "datainicio") //padronizado para minusculo conforme padrao do postgreSQL
    private LocalDate dataInicio;

    @Column(name = "datafim") //padronizado para minusculo conforme padrao do postgreSQL
    private LocalDate dataFim;

    @Column(name = "datacadastro") //padronizado para minusculo conforme padrao do postgreSQL
    private LocalDateTime dataCadastro;

    private String semestre;

    @Column(name = "localatuacao") //adicionado o mapeamento para a coluna 'localAtuacao' do banco de dados, padronizado para minusculo conforme padrao do postgreSQL
    private String localAtuacao;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "disciplinaid") //padronizado para minusculo conforme padrao do postgreSQL
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "professororientadormatricula") //padronizado para minusculo conforme padrao do postgreSQL
    private Professor professor;

    // Construtor vazio obrigatório
    public Monitoria() {
    }

    public Long getId() { 
        return id; 
    }

    public AlunoMonitor getMatriculaAluno() { 
        return matriculaAluno; 
    }
    public void setMatriculaAluno(AlunoMonitor matriculaAluno) {
         this.matriculaAluno = matriculaAluno;
    }

    public String getTipoMonitoria() { 
        return tipoMonitoria; 
    }
    public void setTipoMonitoria(String tipoMonitoria) {
         this.tipoMonitoria = tipoMonitoria;
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

    public String getLocalAtuacao() { 
        return localAtuacao; 
    }
    public void setLocalAtuacao(String localAtuacao) {
        this.localAtuacao = localAtuacao;
    }

    public String getSemestre() { 
        return semestre; 
    }
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public Boolean getStatus() {
     return status; 
    }
    public void setStatus(Boolean status) { 
    this.status = status; 
    }

    public Disciplina getDisciplina() { 
    return disciplina; 
    }
    public void setDisciplina(Disciplina disciplina) {
     this.disciplina = disciplina; 
    }

    public Professor getProfessor() { 
        return professor; 
    }
    public void setProfessor(Professor professor) { 
    this.professor = professor; 
    }
}