package br.com.alura.AluraFake.course;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private int hoursToComplete;
    private Long user_id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime inactivationDate;

    @Deprecated
    public Course(){}

    public Course(String name, String code, int hoursToComplete, Long user_id, String description, Status status) {
        this.name = name;
        this.code = code;
        this.hoursToComplete = hoursToComplete;
        this.user_id = user_id;
        this.description = description;
        this.status = status;
    }

    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public int getHoursToComplete() {
        return hoursToComplete;
    }
    public Long getUser_id() {
        return user_id;
    }
    public String getDescription() {
        return description;
    }
    public Status getStatus() {
        return status;
    }
    public LocalDateTime getInactivationDate() {
        return inactivationDate;
    }

    

    
}
