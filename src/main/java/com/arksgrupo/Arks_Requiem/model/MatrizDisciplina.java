package com.arksgrupo.Arks_Requiem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matrizdisciplina")
@IdClass(MatrizDisciplinaId.class)
public class MatrizDisciplina {
    @Id
    @ManyToOne
    @JoinColumn(name = "matrizId")
    private Matriz matrizId;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "disciplinaId")
    private Disciplina disciplinaId;

    public Matriz getMatriz() {
        return matrizId;
    }
    public void setMatriz(Matriz matrizId){
        this.matrizId = matrizId;
    }

    public Disciplina getDisciplina() {
        return disciplinaId;
    }

    public void setDisciplina(Disciplina disciplinaId) {
        this.disciplinaId = disciplinaId;
    }
}
