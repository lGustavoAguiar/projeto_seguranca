package com.seguranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seguranca.service.Calculadora;

@RestController
@RequestMapping("/api/calculadora")
public class CalculadoraController {
    
    @Autowired
    private Calculadora calculadoraService;

    @GetMapping("/somar")
    public int somar(@RequestParam int a, @RequestParam int b) {
        return calculadoraService.somar(a, b);
    }

    @GetMapping("/subtrair")
    public int subtrair(@RequestParam int a, @RequestParam int b) {
        return calculadoraService.subtrair(a, b);
    }

    @GetMapping("/multiplicar")
    public Double multiplicar(@RequestParam Double a, @RequestParam Double b) {
        return calculadoraService.multiplicar(a, b);
    }

    @GetMapping("/dividir")
    public Double dividir(@RequestParam Double a, @RequestParam Double b) {
        return calculadoraService.dividir(a, b);
    }

    @GetMapping("/ehPar")
    public boolean ehPar(@RequestParam int numero) {    
        return calculadoraService.ehPar(numero);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Calculadora API está funcionando corretamente!";
    }
}

