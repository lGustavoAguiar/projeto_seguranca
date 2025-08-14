package com.seguranca.seguranca.service;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculadoraTest {
    //private final Calculadora ser service = new CalculadoraTest();

    @Autowired
    private Calculadora service;

    @Test
    void testeSoma() {
        int resultado = service.somar(2, 3);
        assertEquals(5, resultado);
    }

    @Test
    void testeDividir() {
        Double resultado = service.dividir(2.0, 3.0);
        assertEquals(0.6666666666666666, resultado);
        }

        @Test
        void testarEhPar() {
            assertFalse(service.ehPar(5));
            assertTrue(service.ehPar(4));
        }

        @Test
        void testarDividirPorZero() {
            Exception exception = assertThrows(IllegalArgumentException.class,()->{
            service.dividir(10.0, 0.0);
            });
            assertEquals("Erro ao dividir", exception.getMessage());
        }

            @Test
            void testarNull(){
                String valor = null;
                assertNull(valor, "O valor deve ser nulo");
    }

    @Test
    void testarObjeto() {
        Calculadora c = new Calculadora();
        assertSame(c, c, "Deve ser a mesma instância");
        assertNotSame(c, service, "Deve ser instâncias diferentes");
    }

    @Test
    void testeArray() {
        int[] esperado = {1, 2, 3};
        int[] atual = {1, 2, 3};
        assertEquals(esperado.length, atual.length, "Os arrays devem ter o mesmo tamanho");
        assertEquals(esperado[0], atual[0], "Os primeiros elementos dos arrays devem ser iguais");
    }

    @Test
    void testeAssertDoesNotThrow() {
        assertDoesNotThrow(() -> {
            service.somar(5, 3);
        }, "A soma não deve lançar exceção");
    }

    @Test
    void testeFail() {
        //fail("Este teste deve falhar intencionalmente para demonstração");
        int resultado = service.somar(2, 2);
        if(resultado != 4) {
            fail("A soma de 2 + 2 deveria ser 4, mas foi " + resultado);
        }
    }

    @Test
    void testAssertTimeout() {
        assertTimeout(Duration.ofSeconds(1), () -> {
            service.somar(1000, 2000);
        }, "A operação deveria terminar em menos de 1 segundo");
    }

    @Test
    void testAssertTimeoutPreemptively() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            service.dividir(100.0, 5.0);
        }, "A operação deveria terminar em menos de 1 segundo");
    }

}