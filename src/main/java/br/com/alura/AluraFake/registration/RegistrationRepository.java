package br.com.alura.AluraFake.registration;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationRepository extends JpaRepository<Registration, Long>{

    @Query("SELECT COUNT(r) > 0 FROM Registration r WHERE r.user.email = :email AND r.course.code = :courseCode")
    boolean existsByUserEmailAndCourseCode(@Param("email") String email, @Param("courseCode") String courseCode);

    @Query(value = "SELECT c.name AS courseName, c.code AS courseCode, u.name AS instructorName, u.email AS instructorEmail, COUNT(r.id) AS registrationCount " +
    "FROM Registration r " +
    "JOIN Course c ON r.courseId = c.id " +
    "JOIN User u ON c.userId = u.id " +
    "GROUP BY c.id, u.id " +
    "ORDER BY registrationCount DESC", nativeQuery = true)
    List<Object[]> findCoursesWithMostRegistrations();
}
