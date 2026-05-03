package com.arksgrupo.Arks_Requiem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "escola")
public class Escola {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @ManyToOne
    @JoinColumn(name = "coordenadorid", referencedColumnName = "matricula")
    private Professor coordenador;
    @ManyToOne
    @JoinColumn(name = "iesid")
    private Ies ies;
    @Column(name = "datacadastro")
    private LocalDateTime dataCadastro;
    private boolean status;
    @OneToMany(mappedBy = "escola")
    private List<Professor> professores;

    //  se não tiver um contrutor vazio da erro. PORQUE???? NÃO SEI //
    public Escola(){
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){ 
        this.id = id;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public Professor getCoordenador(){
        return coordenador;
    }
    public void setCoordenador(Professor coordenador){ 
        this.coordenador = coordenador;
    }

    public Ies getIes(){ 
        return ies;
    }
    public void setIes(Ies ies){
        this.ies = ies;
    }

    public LocalDateTime getDataCadastro(){
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro){
        this.dataCadastro = dataCadastro;
    }

    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){ 
        this.status = status;
    }
}
