package com.curso.entity.projection;

import org.springframework.beans.factory.annotation.Value;

// @author Henrique Andrew da Silva

public interface NomeCidade {
    
    String getNome();
    
    @Value("#{(target.endereco.cidade)}")
    String getCidade();
    
}
