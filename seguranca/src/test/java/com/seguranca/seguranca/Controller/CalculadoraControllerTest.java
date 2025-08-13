package com.seguranca.seguranca.controller;

import com.seguranca.seguranca.service.Calculadora;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculadoraController.class)
class CalculadoraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Calculadora calculadora;

    @Test
    void testeSomar() throws Exception {
        when(calculadora.somar(2, 3)).thenReturn(5);

        mockMvc.perform(get("/calculadora/somar")
                .param("a", "2")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    void testeDividir() throws Exception {
        when(calculadora.dividir(10.0, 2.0)).thenReturn(5.0);

        mockMvc.perform(get("/calculadora/dividir")
                .param("a", "10")
                .param("b", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }

    @Test
    void testeEhPar() throws Exception {
        when(calculadora.ehPar(4)).thenReturn(true);

        mockMvc.perform(get("/calculadora/ehpar")
                .param("numero", "4"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}