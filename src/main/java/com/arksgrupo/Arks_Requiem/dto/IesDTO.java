package com.arksgrupo.Arks_Requiem.dto;

public class IesDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private Boolean status;

    public IesDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}