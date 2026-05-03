package com.arksgrupo.Arks_Requiem.dto;

import java.time.LocalDateTime;

public class DisciplinaDTO {
    private Long id;
    private String sigla;
    private String descricao;
    private Integer cargaHoraria;
    private Boolean status = true;
    private String preRequisitosStr;
    private LocalDateTime dataCadastro;
    private Long escolaId;
    private Long matrizId;


    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public long getEscolaId() {
        return escolaId;
    }

    public void setEscolaId(long escolaId) {
        this.escolaId = escolaId;
    }

    public long getMatrizId() {
        return matrizId;
    }

    public void setMatrizId(long matrizId) {
        this.matrizId = matrizId;
    }


}
