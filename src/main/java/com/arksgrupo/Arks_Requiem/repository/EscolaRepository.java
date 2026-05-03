package com.arksgrupo.Arks_Requiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arksgrupo.Arks_Requiem.model.Escola;

public interface EscolaRepository extends  JpaRepository<Escola, Long>{
/* o spring cria o CRUD automaticamente
save()
findAll()
findById()
delete()
update()
*/    
}
