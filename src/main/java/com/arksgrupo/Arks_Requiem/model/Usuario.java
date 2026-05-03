package com.arksgrupo.Arks_Requiem.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuario") //alterado para bater com o banco de dados 
@Entity(name = "Usuario") 
@Getter
@Setter // adicionado para facilitar manipulacao
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //alterado para suportar o ID Long (BIGINT/SERIAL)
    private Long id;
    
    @Column(name = "login", nullable = false, unique = true) //mapeando o campo 'login' do Java para a coluna 'login' do banco
    private String login;
    
    private String password;

    private Boolean status = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    public Usuario(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = true;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Conta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Conta nunca bloqueia
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Senha nunca expira
    }

    @Override
    public boolean isEnabled() {
        return true; // Usuário sempre ativo
    }
}