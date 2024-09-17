package br.com.alura.AluraFake.registration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.AluraFake.course.Course;
import br.com.alura.AluraFake.course.CourseRepository;
import br.com.alura.AluraFake.user.User;
import br.com.alura.AluraFake.user.UserRepository;

import static br.com.alura.AluraFake.course.Status.ACTIVE;

import java.util.Collections;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {
    
     @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private RegistrationRepository registrationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createRegistration__should_return_bad_request_when_student_not_found() throws Exception {
        NewRegistrationDTO newRegistrationDTO = new NewRegistrationDTO();
        newRegistrationDTO.setStudentEmail("nonexistent@student.com");
        newRegistrationDTO.setCourseCode("valid-code");

        when(userRepository.findByEmail(newRegistrationDTO.getStudentEmail())).thenReturn(null);

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newRegistrationDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.field").value("user"))
                .andExpect(jsonPath("$.message").value("Estudante não encontrado"));
    }

    @Test
    void createRegistration__should_return_bad_request_when_course_not_found() throws Exception {
        NewRegistrationDTO newRegistrationDTO = new NewRegistrationDTO();
        newRegistrationDTO.setStudentEmail("valid@student.com");
        newRegistrationDTO.setCourseCode("invalid-code");

        User user = new User();  

        when(userRepository.findByEmail(newRegistrationDTO.getStudentEmail())).thenReturn(user);
        when(courseRepository.findByCodeAndStatus(newRegistrationDTO.getCourseCode(), ACTIVE)).thenReturn(null);

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newRegistrationDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.field").value("course"))
                .andExpect(jsonPath("$.message").value("Curso não encontrado"));
    }

    @Test
    void createRegistration__should_return_conflict_when_student_already_registered() throws Exception {
        NewRegistrationDTO newRegistrationDTO = new NewRegistrationDTO();
        newRegistrationDTO.setStudentEmail("valid@student.com");
        newRegistrationDTO.setCourseCode("valid-code");

        User user = new User();
        Course course = new Course(); 

        // Mock the repositories
        when(userRepository.findByEmail(newRegistrationDTO.getStudentEmail())).thenReturn(user);
        when(courseRepository.findByCodeAndStatus(newRegistrationDTO.getCourseCode(), ACTIVE)).thenReturn(course);
        when(registrationRepository.existsByUserEmailAndCourseCode(newRegistrationDTO.getStudentEmail(), newRegistrationDTO.getCourseCode()))
                .thenReturn(true);

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newRegistrationDTO)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Estudante já matriculado no curso"));
    }

    @Test
    void createRegistration__should_return_created_when_registration_is_successful() throws Exception {
        NewRegistrationDTO newRegistrationDTO = new NewRegistrationDTO();
        newRegistrationDTO.setStudentEmail("valid@student.com");
        newRegistrationDTO.setCourseCode("valid-code");

        User user = new User(); 
        Course course = new Course(); 
        Registration registration = new Registration();

        // Mock the repositories
        when(userRepository.findByEmail(newRegistrationDTO.getStudentEmail())).thenReturn(user);
        when(courseRepository.findByCodeAndStatus(newRegistrationDTO.getCourseCode(), ACTIVE)).thenReturn(course);
        when(registrationRepository.existsByUserEmailAndCourseCode(newRegistrationDTO.getStudentEmail(), newRegistrationDTO.getCourseCode()))
                .thenReturn(false);
        when(registrationRepository.save(any(Registration.class))).thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newRegistrationDTO)))
                .andExpect(status().isCreated());

    }

    @Test
    void report__should_return_list_of_registration_reports() throws Exception {
        List<Object[]> mockResults = Arrays.asList(
            new Object[]{"Course A", "C-A", "Instructor A", "instructorA@example.com", 10L},
            new Object[]{"Course B", "C-B", "Instructor B", "instructorB@example.com", 20L}
        );
    
        when(registrationRepository.findCoursesWithMostRegistrations()).thenReturn(mockResults);
    
    
        mockMvc.perform(get("/registration/report")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseName").value("Course A"))
                .andExpect(jsonPath("$[0].courseCode").value("C-A"))
                .andExpect(jsonPath("$[0].instructorName").value("Instructor A"))
                .andExpect(jsonPath("$[0].instructorEmail").value("instructorA@example.com"))
                .andExpect(jsonPath("$[0].totalRegistrations").value(10))
                .andExpect(jsonPath("$[1].courseName").value("Course B"))
                .andExpect(jsonPath("$[1].courseCode").value("C-B"))
                .andExpect(jsonPath("$[1].instructorName").value("Instructor B"))
                .andExpect(jsonPath("$[1].instructorEmail").value("instructorB@example.com"))
                .andExpect(jsonPath("$[1].totalRegistrations").value(20));
    }


    @Test
    void report__should_return_empty_list_when_no_data() throws Exception {
        when(registrationRepository.findCoursesWithMostRegistrations()).thenReturn(Collections.emptyList());
    
        mockMvc.perform(get("/registration/report")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }
    

}
