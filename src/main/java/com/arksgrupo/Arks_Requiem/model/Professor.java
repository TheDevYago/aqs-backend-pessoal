package com.arksgrupo.Arks_Requiem.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @Column(name = "matricula")
    private Long matricula;
    
    @Column(name = "nomecompleto") //alterado para bater com o DDL do banco de dados
    private String nomeCompleto;
    
    private String email;
    private String telefone;
    private boolean status;

    @OneToMany(mappedBy = "professor")
    private List<FormacaoProfessor> formacoes;

    @ManyToOne
    @JoinColumn(name = "escolaid") //ajustado para minusculo conforme padrao do postgreSQL 
    private Escola escola; 

    @OneToOne //alterado pois usuarioId é UNIQUE no banco
    @JoinColumn(name = "usuarioid") //ajustado para minusculo conforme padrao do postgreSQL
    private Usuario usuario; 

    @Column(name = "datacadastro")
    private LocalDateTime dataCadastro = LocalDateTime.now();

    // Construtor vazio obrigatório
    public Professor() {
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

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getTelefone() { 
        return telefone; 
    }
    public void setTelefone(String telefone) { 
        this.telefone = telefone; 
    }

    public boolean getStatus() { 
        return status; 
    }
    public void setStatus(boolean status) { 
        this.status = status; 
    }

    public Escola getEscola() {
        return escola; 
    }
    public void setEscola(Escola escola) { 
        this.escola = escola; 
    }

    public Usuario getUsuario(){
        return usuario; 
    }
    public void setUsuario(Usuario usuario) { 
        this.usuario = usuario; 
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}