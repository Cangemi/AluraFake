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
    private Long userId;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime inactivationDate;

    @Deprecated
    public Course(){}

    public Course(String name, String code, int hoursToComplete, Long userId, String description, Status status) {
        this.name = name;
        this.code = code;
        this.hoursToComplete = hoursToComplete;
        this.userId = userId;
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
    public Long userId() {
        return userId;
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
