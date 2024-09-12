package br.com.alura.AluraFake.course;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class NewCourseDTO {

    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    @Length(min = 4, max = 10)
    private String code;
    @NotNull
    @Length(min = 1, max = 40)
    private int hoursToComplete;
    @NotNull
    @NotBlank
    @Email
    private String emailInstructor;

    public NewCourseDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getHoursToComplete() {
        return hoursToComplete;
    }

    public void setHoursToComplete(int hoursToComplete) {
        this.hoursToComplete = hoursToComplete;
    }

    public String getEmailInstructor() {
        return emailInstructor;
    }

    public void setEmailInstructor(String emailInstructor) {
        this.emailInstructor = emailInstructor;
    }
}
