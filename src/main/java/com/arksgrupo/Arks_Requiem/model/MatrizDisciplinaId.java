package com.arksgrupo.Arks_Requiem.model;

import java.io.Serializable;
import java.util.Objects;

// Classe usada para representar a chave composta
// da tabela matriz_disciplina
public class MatrizDisciplinaId implements Serializable {
    private long matrizId;
    private long disciplinaId;

      public MatrizDisciplinaId(){}

      public MatrizDisciplinaId(Long matrizId, Long disciplinaId){
        this.matrizId = matrizId;
        this.disciplinaId = disciplinaId;
    }

     @Override
    public boolean equals(Object o){
        if(this == o) return true;

        if(!(o instanceof MatrizDisciplinaId)) return false;
        MatrizDisciplinaId that = (MatrizDisciplinaId) o;

        return Objects.equals(matrizId, that.matrizId) && Objects.equals(disciplinaId, that.disciplinaId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(matrizId, disciplinaId);
    }
}
