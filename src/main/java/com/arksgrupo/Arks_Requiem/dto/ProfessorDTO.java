package com.arksgrupo.Arks_Requiem.dto;


public class ProfessorDTO {
    private Long matricula;
    private String nome;
    private String email;
    private String telefone;
    private Long escolaId;
    private Long usuarioId;
    private Boolean status;
    private String escolaNome;
    private String dataCadastro;

     // Getters e Setters

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Long getEscolaId() {
        return escolaId;
    }

    public void setEscolaId(Long escolaId) {
        this.escolaId = escolaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getEscolaNome() {
        return escolaNome;
    }

    public void setEscolaNome(String escolaNome) {
        this.escolaNome = escolaNome;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
