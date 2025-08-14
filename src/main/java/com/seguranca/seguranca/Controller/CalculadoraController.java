package com.seguranca.seguranca.Controller;

import com.seguranca.seguranca.service.Calculadora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    @Autowired
    private Calculadora calculadora;

    @GetMapping("/somar")
    public int somar(@RequestParam int a, @RequestParam int b) {
        return calculadora.somar(a, b);
    }

    @GetMapping("/dividir")
    public double dividir(@RequestParam double a, @RequestParam double b) {
        return calculadora.dividir(a, b);
    }

    @GetMapping("/ehpar")
    public boolean ehPar(@RequestParam int numero) {
        return calculadora.ehPar(numero);
    }
}