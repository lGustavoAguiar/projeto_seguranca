package com.seguranca.seguranca.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.seguranca.seguranca.model.Pessoa;
import com.seguranca.seguranca.repository.PessoaRepository;

@SpringBootTest
class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");
        pessoa.setIdade(30);
        pessoa.setEmail("joao@email.com");
    }

    @Test
    void testListarTodos() {
        when(pessoaRepository.findAll()).thenReturn(Arrays.asList(pessoa));
        
        List<Pessoa> pessoas = pessoaService.listarTodos();
        
        assertNotNull(pessoas);
        assertEquals(1, pessoas.size());
        assertEquals("João", pessoas.get(0).getNome());
    }

    @Test
    void testBuscarPorId() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        
        Optional<Pessoa> resultado = pessoaService.buscarPorId(1L);
        
        assertTrue(resultado.isPresent());
        assertEquals("João", resultado.get().getNome());
    }

    @Test
    void testSalvar() {
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        
        Pessoa pessoaSalva = pessoaService.salvar(pessoa);
        
        assertNotNull(pessoaSalva);
        assertEquals("João", pessoaSalva.getNome());
    }

    @Test
    void testAtualizar() {
        when(pessoaRepository.existsById(1L)).thenReturn(true);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        
        Pessoa pessoaAtualizada = pessoaService.atualizar(1L, pessoa);
        
        assertNotNull(pessoaAtualizada);
        assertEquals("João", pessoaAtualizada.getNome());
    }

    @Test
    void testAtualizarPessoaNaoEncontrada() {
        when(pessoaRepository.existsById(1L)).thenReturn(false);
        
        assertThrows(RuntimeException.class, () -> {
            pessoaService.atualizar(1L, pessoa);
        });
    }

    @Test
    void testDeletar() {
        doNothing().when(pessoaRepository).deleteById(1L);
        
        assertDoesNotThrow(() -> pessoaService.deletar(1L));
        
        verify(pessoaRepository, times(1)).deleteById(1L);
    }
}
