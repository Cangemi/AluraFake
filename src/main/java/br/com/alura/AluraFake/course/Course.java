package br.com.alura.AluraFake.course;

import java.time.LocalDateTime;

import br.com.alura.AluraFake.user.User;
import jakarta.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private int hoursToComplete;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime inactivationDate= null;

    @Deprecated
    public Course(){}

    public Course(String name, String code, int hoursToComplete, User user, String description, Status status) {
        this.name = name;
        this.code = code;
        this.hoursToComplete = hoursToComplete;
        this.user = user;
        this.description = description;
        this.status = status;
    }

    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public void setInactivationDate(LocalDateTime inactivationDate) {
        this.inactivationDate = inactivationDate;
    }

    public int getHoursToComplete() {
        return hoursToComplete;
    }
    public User getUserId() {
        return user;
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
