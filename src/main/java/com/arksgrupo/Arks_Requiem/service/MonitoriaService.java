package com.arksgrupo.Arks_Requiem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arksgrupo.Arks_Requiem.dto.MonitoriaDTO;
import com.arksgrupo.Arks_Requiem.exception.ResourceNotFoundException;
import com.arksgrupo.Arks_Requiem.model.Monitoria;
import com.arksgrupo.Arks_Requiem.repository.AlunoMonitorRepository;
import com.arksgrupo.Arks_Requiem.repository.DisciplinaRepository;
import com.arksgrupo.Arks_Requiem.repository.MonitoriaRepository;
import com.arksgrupo.Arks_Requiem.repository.ProfessorRepository;

@Service
public class MonitoriaService {
    @Autowired
    private MonitoriaRepository repository;
    @Autowired
    private AlunoMonitorRepository alunoRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private ProfessorRepository professorRepository;


    // SALVAR MONITORIA
    public MonitoriaDTO salvar(MonitoriaDTO dto){
        Monitoria monitoria = converterParaEntity(dto);
        monitoria.setDataCadastro(LocalDateTime.now());
        monitoria = repository.save(monitoria);

        return converterParaDTO(monitoria);
    }

    // LISTAR TODAS
    public List<MonitoriaDTO> listarTodas(){
        return repository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public MonitoriaDTO buscarPorId(Long id){
        Monitoria monitoria = repository.findById(id).orElseThrow(() -> new RuntimeException("Monitoria não encontrada"));

        return converterParaDTO(monitoria);
    }


    // ATUALIZAR
    public MonitoriaDTO atualizar(Long id, MonitoriaDTO dto){
        Monitoria monitoria = repository.findById(id).orElseThrow(() -> new RuntimeException("Monitoria não encontrada"));

        monitoria.setTipoMonitoria(dto.getTipoMonitoria());
        monitoria.setDataInicio(dto.getDataInicio());
        monitoria.setDataFim(dto.getDataFim());
        monitoria.setSemestre(dto.getSemestre());
        monitoria.setLocalAtuacao(dto.getLocalAtuacao());
        monitoria.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
        monitoria.setMatriculaAluno(alunoRepository.findById(dto.getAlunoMatricula()).orElseThrow());
        monitoria.setDisciplina(disciplinaRepository.findById(dto.getDisciplinaId()).orElseThrow());
        monitoria.setProfessor(professorRepository.findById(dto.getProfessorOrientadorMatricula()).orElseThrow());

        monitoria = repository.save(monitoria);

        return converterParaDTO(monitoria);
    }

    // INATIVAR
    @Transactional
    public void inativar(Long id){
        Monitoria monitoria = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Monitoria não encontrado com id: " + id));
        monitoria.setStatus(false);

        repository.save(monitoria);
    }


    // DTO → ENTITY
    private Monitoria converterParaEntity(MonitoriaDTO dto){
        Monitoria monitoria = new Monitoria();

        monitoria.setTipoMonitoria(dto.getTipoMonitoria());
        monitoria.setDataInicio(dto.getDataInicio());
        monitoria.setDataFim(dto.getDataFim());
        monitoria.setSemestre(dto.getSemestre());
        monitoria.setLocalAtuacao(dto.getLocalAtuacao());
        monitoria.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
        monitoria.setMatriculaAluno(alunoRepository.findById(dto.getAlunoMatricula()).orElseThrow());
        monitoria.setDisciplina(disciplinaRepository.findById(dto.getDisciplinaId()).orElseThrow());
        monitoria.setProfessor(professorRepository.findById(dto.getProfessorOrientadorMatricula()).orElseThrow());

        return monitoria;
    }


    // ENTITY → DTO
    private MonitoriaDTO converterParaDTO(Monitoria monitoria){
        MonitoriaDTO dto = new MonitoriaDTO();

        dto.setId(monitoria.getId());
        dto.setTipoMonitoria(monitoria.getTipoMonitoria());
        dto.setDataInicio(monitoria.getDataInicio());
        dto.setDataFim(monitoria.getDataFim());
        dto.setSemestre(monitoria.getSemestre());
        dto.setLocalAtuacao(monitoria.getLocalAtuacao());
        dto.setStatus(monitoria.getStatus());
        dto.setDataCadastro(monitoria.getDataCadastro());
        dto.setAlunoMatricula(monitoria.getMatriculaAluno().getMatricula());
        dto.setDisciplinaId(monitoria.getDisciplina().getId());
        dto.setProfessorOrientadorMatricula(monitoria.getProfessor().getMatricula());

        return dto;
    }

}
