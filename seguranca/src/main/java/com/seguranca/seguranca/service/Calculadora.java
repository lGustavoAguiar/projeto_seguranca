package com.seguranca.seguranca.service;

import org.springframework.stereotype.Service;

@Service
public class Calculadora {
    
    public int somar(int a, int b) {
        return a + b;
    }

    public double dividir(double a, double b){
        if(b == 0) {
            throw new IllegalArgumentException("Erro ao dividir");
        }
        return a / b;
        }

        public boolean ehPar(int numero) {
            return numero % 2 == 0;
        }
    
}
