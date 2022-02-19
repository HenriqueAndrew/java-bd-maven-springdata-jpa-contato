package com.curso.service;

// @author Henrique Andrew da Silva

import com.curso.entity.Contato;
import com.curso.entity.Endereco;
import com.curso.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ContatoService contatoRepository;
    
    @Transactional(readOnly=false)
    public void salvar(Endereco endereco, Contato contato){
        enderecoRepository.save(endereco);
        contato.setEndereco(endereco);
        contatoRepository.salvar(contato);
    }
    

}
