package com.arksgrupo.Arks_Requiem.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.arksgrupo.Arks_Requiem.dto.ProfessorDTO;
import com.arksgrupo.Arks_Requiem.exception.ResourceNotFoundException;
import com.arksgrupo.Arks_Requiem.model.*;
import com.arksgrupo.Arks_Requiem.repository.*;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;
    @Autowired
    private EscolaRepository escolaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ProfessorDTO salvar(ProfessorDTO dto) {
        // 1. Cria usuário automático
        Usuario novoUsuario = new Usuario(dto.getEmail(), passwordEncoder.encode("123"), UserRole.USER);
        novoUsuario.setStatus(true);
        novoUsuario = usuarioRepository.save(novoUsuario);

        // 2. Mapeia Professor
        Professor professor = new Professor();
        professor.setMatricula(dto.getMatricula());
        professor.setNomeCompleto(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setTelefone(dto.getTelefone());
        professor.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
        professor.setDataCadastro(LocalDateTime.now());
        
        professor.setEscola(escolaRepository.findById(dto.getEscolaId()).orElseThrow(() -> new ResourceNotFoundException("Escola não encontrada")));

        professor.setUsuario(novoUsuario);
        professor = repository.save(professor);

        return converterParaDTO(professor);
    }

    public List<ProfessorDTO> listar() {
        return repository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public ProfessorDTO buscarPorMatricula(Long matricula) {
        Professor professor = repository.findById(matricula).orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));
        return converterParaDTO(professor);
    }

    public ProfessorDTO atualizar(Long matricula, ProfessorDTO dto) {
        Professor professor = repository.findById(matricula).orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));
        professor.setNomeCompleto(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setTelefone(dto.getTelefone());
        professor.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
        professor.setEscola(escolaRepository.findById(dto.getEscolaId()).orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado")));
        
        return converterParaDTO(repository.save(professor));
    }

    @Transactional
    public void inativar(Long matricula) {
        Professor professor = repository.findById(matricula).orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));
        professor.setStatus(false);
        repository.save(professor);
    }

    private ProfessorDTO converterParaDTO(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setMatricula(professor.getMatricula());
        dto.setNome(professor.getNomeCompleto());
        dto.setEmail(professor.getEmail());
        dto.setTelefone(professor.getTelefone());
        dto.setStatus(professor.getStatus());

        if (professor.getEscola() != null) {
            dto.setEscolaId(professor.getEscola().getId());
            dto.setEscolaNome(professor.getEscola().getNome());
        }
        if (professor.getDataCadastro() != null) {
            dto.setDataCadastro(professor.getDataCadastro().toString());
        }
        return dto;
    }


}