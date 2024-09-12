package br.com.alura.AluraFake.registration;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RegistrationController {

    @PostMapping("/registration/new")
    public ResponseEntity createCourse(@Valid @RequestBody NewRegistrationDTO newRegistration) {
        //Questão 3 aqui
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/registration/report")
    public ResponseEntity<List<RegistrationReportItem>> report() {
        List<RegistrationReportItem> items = new ArrayList<>();

        //Questão 4 aqui

        //Dados fakes que devem ser rescrevidos
        items.add(new RegistrationReportItem(
                "Java para iniciantes",
                "java",
                "Caio Bugorin",
                "caio.bugorin@alura.com.br",
                10L
                )
        );

        items.add(new RegistrationReportItem(
                        "Spring para iniciantes",
                        "spring",
                        "Caio Bugorin",
                        "caio.bugorin@alura.com.br",
                        9L
                )
        );

        items.add(new RegistrationReportItem(
                        "Maven para avançados",
                        "mavem",
                        "Caio Bugorin",
                        "caio.bugorin@alura.com.br",
                        9L
                )
        );

        return ResponseEntity.ok(items);
    }


}
