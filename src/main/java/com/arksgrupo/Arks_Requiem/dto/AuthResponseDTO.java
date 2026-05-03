package com.arksgrupo.Arks_Requiem.dto;

public class AuthResponseDTO {
    private String token;
    private UsuarioDTO usuario;

    public AuthResponseDTO(String token, UsuarioDTO usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }
}
