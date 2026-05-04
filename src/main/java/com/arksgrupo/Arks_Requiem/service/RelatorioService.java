package com.arksgrupo.Arks_Requiem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException; 

import java.time.LocalDate;
import java.util.List;
import java.io.ByteArrayOutputStream;

import com.arksgrupo.Arks_Requiem.dto.RelatorioFiltroDTO;
import com.arksgrupo.Arks_Requiem.model.Escola;
import com.arksgrupo.Arks_Requiem.model.Relatorio;
import com.arksgrupo.Arks_Requiem.repository.EscolaRepository;
import com.arksgrupo.Arks_Requiem.repository.RelatorioRepository;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository repository;

    @Autowired
    private EscolaRepository escolaRepository;

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

    public ResponseEntity<byte[]> gerarArquivoRelatorio(String tipoId, RelatorioFiltroDTO filtro) {
        
        LocalDate inicio = filtro.getDataInicio() != null ? filtro.getDataInicio(): LocalDate.now().minusMonths(6);
        LocalDate fim = filtro.getDataFim() != null ? filtro.getDataFim(): LocalDate.now();
        String semestre = filtro.getSemestre();
        String formato = (filtro.getFormato() != null) ? filtro.getFormato().toLowerCase() : "pdf";
        
        byte[] conteudo = null;

        switch (tipoId.toLowerCase()) {
            case "escolas":
                List<Escola> listaEscolas = escolaRepository.findAll();
                if(filtro.getFormato().equalsIgnoreCase("excel")) {
                    conteudo = gerarExcelEscolas(listaEscolas);
                } else {
                    conteudo = gerarPdfEscolas(listaEscolas);
                }
                break;

            case "professores":
            case "cursos":
            case "disciplinas":
            case "alunos":
            case "monitores":
            case "monitorias":

            case "quantitativo":
                if (semestre == null || semestre.trim().isEmpty()) {
                    throw new RuntimeException("O semestre é obrigatório para relatórios quantitativos.");
                }
                conteudo = "Relatorio Quantitativo Gerado com Sucesso".getBytes();
                break;

            default:
                conteudo = ("Conteúdo do Relatório de " + tipoId.toUpperCase()).getBytes();
                throw new RuntimeException("ID de relatório não reconhecido: " + tipoId);
        }

        MediaType mediaType = formatoMin.equals("excel") ? MediaType.parseMediaType("application/vnd.ms-excel") : MediaType.APPLICATION_PDF;

        String extensao = formato.equals("excel") ? ".xls" : ".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_" + tipoId + (formato.equals("excel") ? ".xls" : ".pdf"));
        
        return ResponseEntity.ok().headers(headers).contentType(mediaType).body(conteudo);
    }

    private byte[] gerarPdfEscolas(List<Escola> escolas) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Sistema de Monitoria - Arks Requiem").setBold().setFontSize(10));
            document.add(new Paragraph("Relatório: Lista de Escolas Cadastradas").setBold().setFontSize(16));
            document.add(new Paragraph("Gerado em: " + LocalDate.now()).setFontSize(10));
            document.add(new Paragraph("\n"));

            Table table = new Table(UnitValue.createPercentArray(new float[]{1, 4, 3, 3, 2})).useAllAvailableWidth();
            table.addHeaderCell(new Paragraph("ID").setBold());
            table.addHeaderCell(new Paragraph("Nome da Escola").setBold());
            table.addHeaderCell(new Paragraph("Coordenador").setBold());
            table.addHeaderCell(new Paragraph("IES").setBold());
            table.addHeaderCell(new Paragraph("Status").setBold());

            for (Escola e : escolas) {
                table.addCell(new Paragraph(e.getId().toString()));
                table.addCell(new Paragraph(e.getNome() != null ? e.getNome() : "Sem nome"));
                table.addCell(new Paragraph(e.getCoordenador() != null ? e.getCoordenador().getNome() : "Não atribuído"));
                table.addCell(new Paragraph(e.getIes() != null ? e.getIes().getSigla() : "N/A"));
                table.addCell(new Paragraph(e.getStatus() ? "Ativo" : "Inativo"));
            }

            document.add(table);
            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Falha ao gerar binário do PDF de Escolas", e);
        }
    }
}