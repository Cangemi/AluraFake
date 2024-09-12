package br.com.alura.AluraFake.course;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @PostMapping("/course/new")
    public ResponseEntity createCourse(@Valid @RequestBody NewCourseDTO newCourse) {
        // Questão 1 aqui
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/course/{code}/inactive")
    public ResponseEntity createCourse(@PathVariable("code") String courseCode) {
        // Questão 2 aqui
        return ResponseEntity.ok().build();
    }

}
