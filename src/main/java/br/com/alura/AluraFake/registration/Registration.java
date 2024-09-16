package br.com.alura.AluraFake.registration;

import java.time.LocalDateTime;

import br.com.alura.AluraFake.course.Course;
import br.com.alura.AluraFake.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Registration {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    private LocalDateTime registrationDate = LocalDateTime.now();

    public Registration() {}

    public Registration(User user,Course course) {
        this.user = user;
        this.course = course;
    }
    


    public void setUser(User user) {
        this.user = user;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }


}
