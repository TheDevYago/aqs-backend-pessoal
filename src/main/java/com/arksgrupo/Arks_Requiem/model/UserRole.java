package com.arksgrupo.Arks_Requiem.model;

public enum UserRole {
    // Definimos os dois únicos estados possíveis para um perfil
    ADMIN("admin"),
    USER("user");

    private String role;

    // Construtor da enum
    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}