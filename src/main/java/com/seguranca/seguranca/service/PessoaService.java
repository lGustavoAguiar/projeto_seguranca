package com.seguranca.seguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguranca.seguranca.model.Pessoa;
import com.seguranca.seguranca.repository.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }
    
    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }
    
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
    
    public Pessoa atualizar(Long id, Pessoa pessoa) {
        if (pessoaRepository.existsById(id)) {
            pessoa.setId(id);
            return pessoaRepository.save(pessoa);
        }
        throw new RuntimeException("Pessoa não encontrada");
    }
    
    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }
}
