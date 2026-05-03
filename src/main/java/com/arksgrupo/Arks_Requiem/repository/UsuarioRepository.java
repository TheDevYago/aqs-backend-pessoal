package com.arksgrupo.Arks_Requiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.arksgrupo.Arks_Requiem.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Está parte serve para o Spring entender o que quer buscar na coluna 'login' 
    // e retorna um UserDetails (que sua classe Usuario implementa)
    UserDetails findByLogin(String login);
}
