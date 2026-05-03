package com.arksgrupo.Arks_Requiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arksgrupo.Arks_Requiem.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
/* o spring cria o CRUD automaticamente
save()
findAll()
findById()
delete()
update()
*/
}
