package br.com.alura.AluraFake.registration;

import br.com.alura.AluraFake.course.Course;
import br.com.alura.AluraFake.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NewRegistrationDTO {
    @NotBlank
    @NotNull
    private String courseCode;
    @NotBlank
    @NotNull
    @Email
    private String studentEmail;

    public NewRegistrationDTO() {}

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Registration toModel(User user,Course course){
        return new Registration(user,course);
    }
}
