package com.arksgrupo.Arks_Requiem.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.arksgrupo.Arks_Requiem.model.Matriz;
import org.springframework.stereotype.Repository;

@Repository
public interface MatrizRepository extends JpaRepository<Matriz, Long> {
    // Busca matriz ativa de um curso específico
    // apenas uma matriz ativa por curso
Optional<Matriz> findByCursoIdAndStatusTrue(Long cursoId);

}