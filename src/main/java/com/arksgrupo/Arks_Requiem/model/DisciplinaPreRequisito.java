package com.arksgrupo.Arks_Requiem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "DisciplinaPreRequisito")
@Table(name = "disciplina_pre_requisito") 
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DisciplinaPreRequisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "pre_requisito_id")
    private Disciplina preRequisito;

    public DisciplinaPreRequisito() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Disciplina getPreRequisito() {
        return preRequisito;
    }

    public void setPreRequisito(Disciplina preRequisito) {
        this.preRequisito = preRequisito;
    }
}