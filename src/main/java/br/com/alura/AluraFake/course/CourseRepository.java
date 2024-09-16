package br.com.alura.AluraFake.course;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>{
    
    boolean existsByCode(String code);

    Course findByCode(String courseCode);
}
