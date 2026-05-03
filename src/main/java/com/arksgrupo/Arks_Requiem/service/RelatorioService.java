package com.arksgrupo.Arks_Requiem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException; 
import java.time.LocalDate;
import java.util.List;

import com.arksgrupo.Arks_Requiem.model.Relatorio;
import com.arksgrupo.Arks_Requiem.repository.RelatorioRepository;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository repository;

    public Relatorio salvaRelatorio(Relatorio relatorio) {
        return repository.save(relatorio);
    }

    public List<Relatorio> listar() {
        return repository.findAll();
    }

    public Relatorio buscarId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Relatório não encontrado com ID: " + id));
    }

    public Relatorio atualizar(Long id, Relatorio novosDados) {
        return repository.findById(id).map(relatorio -> {
            relatorio.setOcorrencias(novosDados.getOcorrencias());
            relatorio.setParecerFinal(novosDados.getParecerFinal());
            relatorio.setQtdAlunosAtendidos(novosDados.getQtdAlunosAtendidos());
            return repository.save(relatorio);
        }).orElseThrow(() -> new EntityNotFoundException("Não foi possível atualizar: Relatório inexistente."));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Relatório não encontrado para exclusão.");
        }
        repository.deleteById(id);
    }

    public ResponseEntity<byte[]> gerarArquivoRelatorio(String tipoId, LocalDate inicio, LocalDate fim, String semestre, String formato) {
        
        if (inicio == null) { inicio = LocalDate.now().minusMonths(6); }
        if (fim == null) { fim = LocalDate.now(); }

        String formatoMin = formato.toLowerCase();
        byte[] conteudo;

        switch (tipoId.toLowerCase()) {
            case "escolas":
            case "professores":
            case "cursos":
            case "disciplinas":
            case "alunos":
            case "monitores":
            case "monitorias":
                conteudo = ("Conteúdo do Relatório de " + tipoId.toUpperCase()).getBytes();
                break;

            case "quantitativo":
                if (semestre == null || semestre.trim().isEmpty()) {
                    throw new RuntimeException("O semestre é obrigatório para relatórios quantitativos.");
                }
                conteudo = "Relatorio Quantitativo Gerado com Sucesso".getBytes();
                break;

            default:
                throw new RuntimeException("ID de relatório não reconhecido: " + tipoId);
        }

        MediaType mediaType = formatoMin.equals("excel") 
            ? MediaType.parseMediaType("application/vnd.ms-excel") 
            : MediaType.APPLICATION_PDF;

        String extensao = formatoMin.equals("excel") ? ".xls" : ".pdf";
        
        HttpHeaders headers = new HttpHeaders();
        // Permite que o browser entenda que é um download
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_" + tipoId + extensao);

        return ResponseEntity.ok().headers(headers).contentType(mediaType).body(conteudo);
    }
}