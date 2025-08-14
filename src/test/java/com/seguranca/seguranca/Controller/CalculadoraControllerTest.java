package com.seguranca.seguranca.Controller;

import com.seguranca.seguranca.service.Calculadora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CalculadoraControllerTest {

    private MockMvc mockMvc;

    @Mock
    private Calculadora calculadora;

    @InjectMocks
    private CalculadoraController calculadoraController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(calculadoraController).build();
    }

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