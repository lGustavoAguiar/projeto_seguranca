package com.seguranca.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes de integração da CalculadoraController")
public class CalculadoraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Testa o endpoint de soma")
    public void testSomar() throws Exception {
        mockMvc.perform(get("/api/calculadora/somar")
                .param("a", "5")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("8"));
    }

    @Test
    @DisplayName("Testa o endpoint de subtração")
    void testSubtrair() throws Exception {
        mockMvc.perform(get("/api/calculadora/subtrair")
                .param("a", "5")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    @DisplayName("Testa o endpoint de multiplicação")
    void testMultiplicar() throws Exception {
        mockMvc.perform(get("/api/calculadora/multiplicar")
                .param("a", "5.0")
                .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("15.0"));
    }

    @Test
    @DisplayName("Testa o endpoint de divisão")
    void testDividir() throws Exception {
        mockMvc.perform(get("/api/calculadora/dividir")
                .param("a", "6.0")
                .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0"));
    }

    @Test
    @DisplayName("Testa o endpoint de verificação de número par")
    void testEhPar() throws Exception {
        mockMvc.perform(get("/api/calculadora/ehPar")
                .param("numero", "4"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mockMvc.perform(get("/api/calculadora/ehPar")
                .param("numero", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    @DisplayName("Testa o endpoint de health check")
    void testHealthCheck() throws Exception {
        mockMvc.perform(get("/api/calculadora/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Calculadora API está funcionando corretamente!"));
    }
}
