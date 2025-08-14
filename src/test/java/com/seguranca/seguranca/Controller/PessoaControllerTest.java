package com.seguranca.seguranca.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seguranca.seguranca.model.Pessoa;
import com.seguranca.seguranca.service.PessoaService;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
        
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");
        pessoa.setIdade(30);
        pessoa.setEmail("joao@email.com");
    }

    @Test
    void testListarTodos() throws Exception {
        when(pessoaService.listarTodos()).thenReturn(Arrays.asList(pessoa));

        mockMvc.perform(get("/pessoas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[0].idade").value(30));
    }

    @Test
    void testBuscarPorId() throws Exception {
        when(pessoaService.buscarPorId(1L)).thenReturn(Optional.of(pessoa));

        mockMvc.perform(get("/pessoas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.idade").value(30));
    }

    @Test
    void testBuscarPorIdNaoEncontrado() throws Exception {
        when(pessoaService.buscarPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/pessoas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCriar() throws Exception {
        when(pessoaService.salvar(any(Pessoa.class))).thenReturn(pessoa);

        mockMvc.perform(post("/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.idade").value(30));
    }

    @Test
    void testAtualizar() throws Exception {
        when(pessoaService.atualizar(any(Long.class), any(Pessoa.class))).thenReturn(pessoa);

        mockMvc.perform(put("/pessoas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.idade").value(30));
    }

    @Test
    void testDeletar() throws Exception {
        mockMvc.perform(delete("/pessoas/1"))
                .andExpect(status().isOk());
    }
}
