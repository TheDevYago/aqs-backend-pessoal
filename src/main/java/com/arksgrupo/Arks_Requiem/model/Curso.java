package com.arksgrupo.Arks_Requiem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "curso")
public class Curso {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) // chave primaria //
private Long id;

private String sigla;
private String descricao;
private String turno;
@Column(name = "coordenador")
private String coordenador;
@Column(name = "dataCadastro")
private LocalDateTime dataCadastro;
private boolean status;
@ManyToOne
@JoinColumn(name = "escolaId")
private Escola escola;

    public Curso(){
    } // se não tiver um contrutor vazio da erro. POQUE???? NÃO SEI //

    // Getters e Setters
    public Long getId(){
        return id;
    }

    public String getSigla(){
        return sigla;
    }
    public void setSigla(String sigla){
        this.sigla = sigla;
    }

    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public Escola getEscola(){
        return escola;
    }
    public void setEscola(Escola escola){
        this.escola = escola;
    }

    public String getTurno(){
        return turno;
    }
    public void setTurno(String turno){
        this.turno = turno;
    }

    public String getCoordenador() {
        return coordenador;
    }
    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
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
}
