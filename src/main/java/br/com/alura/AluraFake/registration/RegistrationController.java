package br.com.alura.AluraFake.registration;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.AluraFake.course.Course;
import br.com.alura.AluraFake.course.CourseRepository;
import br.com.alura.AluraFake.user.User;
import br.com.alura.AluraFake.user.UserRepository;
import br.com.alura.AluraFake.util.ErrorItemDTO;
import static br.com.alura.AluraFake.course.Status.ACTIVE;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RegistrationController {

    private final RegistrationRepository registrationRepository;

    
    public RegistrationController(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/registration/new")
    public ResponseEntity createCourse(@Valid @RequestBody NewRegistrationDTO newRegistration) {
        
        User user = userRepository.findByEmail(newRegistration.getStudentEmail());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorItemDTO("user", "Estudante não encontrado"));
        }

        Course course = courseRepository.findByCodeAndStatus(newRegistration.getCourseCode(), ACTIVE);

        if (course == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorItemDTO("course", "Curso não encontrado"));
        }
    
        if (registrationRepository.existsByUserEmailAndCourseCode(newRegistration.getStudentEmail(), newRegistration.getCourseCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Estudante já matriculado no curso");
        }
        Registration registration = newRegistration.toModel(user, course);
        registrationRepository.save(registration);

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
