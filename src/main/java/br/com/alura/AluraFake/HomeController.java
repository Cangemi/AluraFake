package br.com.alura.AluraFake;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return "<h1>Bem vindo ao teste de java jr Alura</h1>";
    }
}
