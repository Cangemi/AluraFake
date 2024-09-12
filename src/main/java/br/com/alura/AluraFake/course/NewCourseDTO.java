package br.com.alura.AluraFake.course;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import static br.com.alura.AluraFake.course.Status.ACTIVE;


public class NewCourseDTO {

    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+(-[a-zA-Z]+)*$", message = "Código precisa ser textual e pode ser separado por -")
    @Length(min = 4, max = 10)
    private String code;
    @NotNull
     @Min(1) 
    @Max(40) 
    private int hoursToComplete;

    @NotNull
    private String description;
    
    //Retirado emailInstructor e incluido o instructorID pois decidi fazer a tabela Couser relacionada com a tabela User que já contem email
    @NotNull
    private Long instructorId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Course toModel(){
        return new Course(title,code,hoursToComplete,instructorId,description, ACTIVE);
    }
}
