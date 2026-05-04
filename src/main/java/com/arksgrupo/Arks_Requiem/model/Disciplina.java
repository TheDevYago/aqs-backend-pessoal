package com.arksgrupo.Arks_Requiem.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "disciplina")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sigla;
    private String descricao;
    @Column(name = "cargahoraria", nullable = false)
    private Integer cargaHoraria;
    private boolean status;
    private String preRequisitosStr; // foi adicionado o preRequisitosStr para salvar o que está sendo enviado para o Angular
    private LocalDateTime dataCadastro;
    @ManyToOne
    @JoinColumn(name = "escolaid")
    private Escola escola;

    // Essa oparte é sobre o relacionamento com a ManyToOne muitas disciplinas pertencem a uma Matriz
    @ManyToOne
    @JoinColumn(name = "matrizid")
    private Matriz matriz;

    public Disciplina() {
    }


    public Long getId() { 
        return id; 
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

    public Integer getCargaHoraria() { 
        return cargaHoraria; 
    }
    public void setCargaHoraria(Integer cargaHoraria) { 
        this.cargaHoraria = cargaHoraria; 
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

    public String getPreRequisitosStr() {
        return preRequisitosStr;
    }
    public void setPreRequisitosStr(String preRequisitosStr) {
        this.preRequisitosStr = preRequisitosStr;
    }

   
    public Escola getEscola() { 
        return escola; 
    }
    public void setEscola(Escola escola) { 
        this.escola = escola; 
    }

    public Matriz getMatriz() { 
        return matriz; 
    }
    public void setMatriz(Matriz matriz) {
         this.matriz = matriz; 
    }
}