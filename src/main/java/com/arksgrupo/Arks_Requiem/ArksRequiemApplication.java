package com.arksgrupo.Arks_Requiem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArksRequiemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArksRequiemApplication.class, args);
    }

   // apaguei o @Bean de configuração de CORS, pois o Spring Security já tem uma configuração de CORS integrada que pode ser personalizada no SecurityConfig. Se precisar de uma configuração específica, é melhor configurar diretamente lá para evitar conflitos.

}
