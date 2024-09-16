package br.com.alura.AluraFake.registration;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationRepository extends JpaRepository<Registration, Long>{

    @Query("SELECT COUNT(r) > 0 FROM Registration r WHERE r.user.email = :email AND r.course.code = :courseCode")
    boolean existsByUserEmailAndCourseCode(@Param("email") String email, @Param("courseCode") String courseCode);
}
