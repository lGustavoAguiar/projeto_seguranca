package com.seguranca.service;

import org.springframework.stereotype.Service;

@Service
public class Calculadora {

    public int somar(int a, int b) {
        return a + b;
    }

    public Double dividir(Double a, Double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Erro ao dividir");
        }
        return a / b;
    }

    public boolean ehPar(int numero) {
        return numero % 2 == 0;
    }

    public Double multiplicar(Double a, Double b) {
        return a * b;
    }

    public int subtrair(int a, int b) {
        return a - b;
    }
}
