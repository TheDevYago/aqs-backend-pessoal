package com.arksgrupo.Arks_Requiem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arksgrupo.Arks_Requiem.dto.EscolaDTO;
import com.arksgrupo.Arks_Requiem.exception.ResourceNotFoundException;
import com.arksgrupo.Arks_Requiem.model.Escola;
import com.arksgrupo.Arks_Requiem.model.Professor;
import com.arksgrupo.Arks_Requiem.repository.EscolaRepository;
import com.arksgrupo.Arks_Requiem.repository.IesRepository;
import com.arksgrupo.Arks_Requiem.repository.ProfessorRepository;

@Service
public class EscolaService {
    @Autowired
    private EscolaRepository repository;
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private IesRepository iesRepository;

    public EscolaDTO salvar(EscolaDTO dto){
        Escola escola = converterParaEntity(dto);
        escola = repository.save(escola);

        return converterParaDTO(escola);
    }

    // LISTAR
    public List<EscolaDTO> listar(){
        return repository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public EscolaDTO buscarPorId(Long id){
        Escola escola = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Escola não encontrada com id: " + id));

        return converterParaDTO(escola);
    }

    // ATUALIZAR
    @Transactional
    public EscolaDTO atualizar(Long id, EscolaDTO dto){
        Escola escola = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Escola não encontrada com id: " + id));

        escola.setNome(dto.getNome());

        Professor coordenador = professorRepository.findById(dto.getCoordenadorId()).orElseThrow(() -> new ResourceNotFoundException("Coordenador não encontrado"));

        escola.setCoordenador(coordenador);
        escola.setIes(iesRepository.findById(dto.getIesId()).orElseThrow(() -> new ResourceNotFoundException("IES não encontrada")));
        escola.setDataCadastro(dto.getDataCadastro());
        escola.setStatus(Boolean.TRUE.equals(dto.getStatus()));

        escola = repository.save(escola);

        return converterParaDTO(escola);
        }


    // INATIVAR
    @Transactional // [AJUSTE]
    public void inativar(Long id){ // [AJUSTE] é mais recomendado usar "inativar" para indicar que o registro não será deletado, mas sim marcado como inativo.
        Escola escola = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Escola não encontrada com id: " + id));
        escola.setStatus(false);

        repository.save(escola);
    }


    // DTO → ENTITY
    private Escola converterParaEntity(EscolaDTO dto){
        Escola escola = new Escola();

        escola.setNome(dto.getNome());
        escola.setCoordenador(professorRepository.findById(dto.getCoordenadorId()).orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado")));
        escola.setIes(iesRepository.findById(dto.getIesId()).orElseThrow(() -> new ResourceNotFoundException("IES não encontrada")));
        escola.setDataCadastro(dto.getDataCadastro());
        escola.setStatus(Boolean.TRUE.equals(dto.getStatus()));

        return escola;
    }


    // ENTITY → DTO
    private EscolaDTO converterParaDTO(Escola escola){
        EscolaDTO dto = new EscolaDTO();

        dto.setId(escola.getId());
        dto.setNome(escola.getNome());
        if (escola.getCoordenador() != null) {
            dto.setCoordenadorId(escola.getCoordenador().getMatricula());
        } else {
            dto.setCoordenadorId(null);
        }
        if (escola.getIes() != null) {
            dto.setIesId(escola.getIes().getId());
        } else {
            dto.setIesId(null);
        }
        dto.setDataCadastro(escola.getDataCadastro());
        dto.setStatus(Boolean.TRUE.equals(dto.getStatus()));

        return dto;

    }

    
}

  