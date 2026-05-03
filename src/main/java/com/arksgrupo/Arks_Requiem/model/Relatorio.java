package com.arksgrupo.Arks_Requiem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity (name = "Relatorio")
@Table(name = "MonitoriaRelatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String ocorrencias;

    @Column(columnDefinition = "TEXT")
    private String parecerFinal;

    private Integer qtdAlunosAtendidos;

    private LocalDateTime dataRegistro = LocalDateTime.now();

    // Esse é o vínculo com o Monitor que você já tem
    @ManyToOne
    @JoinColumn(name = "monitorId")
    private Monitoria monitor;

    public Relatorio() {

    }

    public Long getId() { 
        return id; 
    }

    public String getOcorrencias() { 
        return ocorrencias; 
    }
    
    public void setOcorrencias(String ocorrencias) { 
        this.ocorrencias = ocorrencias; 
    }
    
    public String getParecerFinal() { 
        return parecerFinal; 
    }
    
    public void setParecerFinal(String parecerFinal) { 
        this.parecerFinal = parecerFinal; 
    }
    
    public Integer getQtdAlunosAtendidos() { 
        return qtdAlunosAtendidos; 
    }
    
    public void setQtdAlunosAtendidos(Integer qtdAlunosAtendidos) { 
        this.qtdAlunosAtendidos = qtdAlunosAtendidos; 
    }
    
    public Monitoria getMonitor() { 
        return monitor; 
    }
    
    public void setMonitor(Monitoria monitor) { 
        this.monitor = monitor; 
    }
    public LocalDateTime getDataRegistro() {
    return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
    this.dataRegistro = dataRegistro;
    }
}