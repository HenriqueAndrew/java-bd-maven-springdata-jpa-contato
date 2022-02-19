//Henrique Andrew da Silva
package com.curso.service;

import com.curso.entity.Contato;
import com.curso.repository.ContatoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public void salvar(Contato contato) {
        repository.save(contato);
    }

    public Optional<Contato> buscaPorId(Long id) {
        return repository.findById(id);
    }

    public List<Contato> buscarTodos() {
        return repository.findAll();
    }

    public List<Contato> buscarPoridadeQBE(Integer idade) {

        Contato contato = new Contato();
        contato.setIdade(idade);
        Example<Contato> example = Example.of(contato);

        return repository.findAll(example);
    }

    public List<Contato> buscarPorNomeQBE(String nome) {

        Contato contato = new Contato();
        contato.setNome(nome);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.ENDING);

        Example<Contato> example = Example.of(contato, matcher);

        return repository.findAll(example);
    }

    public List<Contato> buscarTodosPorIdadeAsc() {

        Sort sort = new Sort(Direction.ASC, "idade");
        return repository.findAll(sort);
    }

    public Page<Contato> paginaRresultados() {

        Sort sort = new Sort(Direction.ASC, "nome");
        Pageable pageable = new PageRequest(0, 3, sort);
        return repository.findAll(pageable);
    }
}
