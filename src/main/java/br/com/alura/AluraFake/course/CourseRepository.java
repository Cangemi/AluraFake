package br.com.alura.AluraFake.course;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>{
    
    boolean existsByCode(String code);

    Optional<Course> findByCode(String courseCode);
}
