package br.com.alura.AluraFake.course;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static br.com.alura.AluraFake.user.Role.STUDENT;
import static br.com.alura.AluraFake.course.Status.INACTIVE;

import java.time.LocalDateTime;
import br.com.alura.AluraFake.user.User;
import br.com.alura.AluraFake.user.UserRepository;
import br.com.alura.AluraFake.util.ErrorItemDTO;


@RestController
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/course/new")
    public ResponseEntity createCourse(@Valid @RequestBody NewCourseDTO newCourse) {
        if(courseRepository.existsByCode(newCourse.getCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorItemDTO("code", "Já existe um curso cadastrado com este código"));
        }

        if(userRepository.existsByEmailAndRole(newCourse.getEmailInstructor(),STUDENT)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorItemDTO("userId", "Usuário não é um instrutor"));
        }

        User user = userRepository.findByEmail(newCourse.getEmailInstructor());

        Course course = newCourse.toModel(user);
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/course/{code}/inactive")
    public ResponseEntity inactivateCourse(@PathVariable("code") String courseCode) {
        Course course = courseRepository.findByCode(courseCode);

        if(course == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Curso não encontrado.");
        }

        if (course.getStatus().equals(INACTIVE)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("O curso já está inativo.");            
        }
        
        course.setStatus(INACTIVE);
        course.setInactivationDate(LocalDateTime.now());

        courseRepository.save(course);

        return ResponseEntity.ok().build();
    }

}
