package com.arksgrupo.Arks_Requiem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arksgrupo.Arks_Requiem.model.DisciplinaPreRequisito;
import com.arksgrupo.Arks_Requiem.repository.DisciplinaPreRequisitoRepository;
import java.util.List;

@Service
public class DisciplinaPreRequisitoService {

    @Autowired
    private DisciplinaPreRequisitoRepository repository;

    public DisciplinaPreRequisito salvar(DisciplinaPreRequisito vinculo) {
        return repository.save(vinculo);
    }

    public List<DisciplinaPreRequisito> listarTodos() {
        return repository.findAll();
    }
    
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}