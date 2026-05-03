package com.arksgrupo.Arks_Requiem.dto;

import java.time.LocalDateTime;


public class AlunoMonitorDTO {
    private Long matricula;
    private String nome;
    private LocalDateTime dataCadastro;
    private Boolean status;

    public AlunoMonitorDTO() {}
    public LocalDateTime getdataCadastro() { return dataCadastro; }
    public void setdataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
    public Long getMatricula() { return matricula; }
    public void setMatricula(Long matricula) { this.matricula = matricula; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}