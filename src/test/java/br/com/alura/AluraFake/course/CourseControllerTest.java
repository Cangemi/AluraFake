package br.com.alura.AluraFake.course;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.AluraFake.user.UserRepository;

import static br.com.alura.AluraFake.user.Role.STUDENT;
import static br.com.alura.AluraFake.course.Status.INACTIVE;
import static br.com.alura.AluraFake.course.Status.ACTIVE;


@WebMvcTest(CourseController.class)
public class CourseControllerTest {

   @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCourse__should_return_bad_request_when_course_code_has_numbers() throws Exception {
        NewCourseDTO newCourseDTO = new NewCourseDTO();
        newCourseDTO.setCode("course01");
        newCourseDTO.setEmailInstructor("instructor@test.com");
        newCourseDTO.setDescription("Description");
        newCourseDTO.setHoursToComplete(20);
        newCourseDTO.setTitle("Title");

        when(courseRepository.existsByCode(newCourseDTO.getCode())).thenReturn(true);

        mockMvc.perform(post("/course/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCourseDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].field").value("code"))
                .andExpect(jsonPath("$[0].message").value("Código precisa ser textual e pode ser separado por -"));
    }


    @Test
    void createCourse__should_return_bad_request_when_course_code_already_exists() throws Exception {
        NewCourseDTO newCourseDTO = new NewCourseDTO();
        newCourseDTO.setCode("COURSEONE");
        newCourseDTO.setEmailInstructor("instructor@test.com");
        newCourseDTO.setDescription("Description");
        newCourseDTO.setHoursToComplete(20);
        newCourseDTO.setTitle("Title");

        when(courseRepository.existsByCode(newCourseDTO.getCode())).thenReturn(true);

        mockMvc.perform(post("/course/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCourseDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.field").value("code"))
                .andExpect(jsonPath("$.message").value("Já existe um curso cadastrado com este código"));
    }

    @Test
    void createCourse__should_return_bad_request_when_user_is_not_instructor() throws Exception {
        NewCourseDTO newCourseDTO = new NewCourseDTO();
        newCourseDTO.setCode("validCode");
        newCourseDTO.setEmailInstructor("student@test.com");
        newCourseDTO.setDescription("Description");
        newCourseDTO.setHoursToComplete(20);
        newCourseDTO.setTitle("Title");

        when(courseRepository.existsByCode(newCourseDTO.getCode())).thenReturn(false);
        when(userRepository.existsByEmailAndRole(newCourseDTO.getEmailInstructor(), STUDENT)).thenReturn(true);

        mockMvc.perform(post("/course/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCourseDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.field").value("userId"))
                .andExpect(jsonPath("$.message").value("Usuário não é um instrutor"));
    }


    @Test
    void createCourse__should_return_created_when_course_request_is_valid() throws Exception {
        NewCourseDTO newCourseDTO = new NewCourseDTO();
        newCourseDTO.setCode("COURSEONE");
        newCourseDTO.setEmailInstructor("instructor@test.com");
        newCourseDTO.setDescription("Description");
        newCourseDTO.setHoursToComplete(20);
        newCourseDTO.setTitle("Title");


        when(courseRepository.existsByCode(newCourseDTO.getCode())).thenReturn(false);

  
        Course savedCourse = newCourseDTO.toModel(null);
        when(courseRepository.save(any(Course.class))).thenReturn(savedCourse);

            mockMvc.perform(post("/course/new")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newCourseDTO)))
            .andExpect(status().isCreated());
    }


    @Test
    void inactivateCourse__should_return_not_found_when_course_does_not_exist() throws Exception {
        String courseCode = "nonexistent-code";

        when(courseRepository.findByCode(courseCode)).thenReturn(null);

        mockMvc.perform(post("/course/{code}/inactive", courseCode))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.field").value("course"))
                .andExpect(jsonPath("$.message").value("Curso não encontrado"));
    }

    @Test
    void inactivateCourse__should_return_bad_request_when_course_already_inactive() throws Exception {
        String courseCode = "existing-code";
        Course existingCourse = new Course();
        existingCourse.setStatus(INACTIVE); 


        when(courseRepository.findByCode(courseCode)).thenReturn(existingCourse);

        mockMvc.perform(post("/course/{code}/inactive", courseCode))
        .andExpect(jsonPath("$.field").value("status"))
        .andExpect(jsonPath("$.message").value("O curso já está inativo"));
    }

    @Test
    void inactivateCourse__should_return_ok_when_course_is_successfully_inactivated() throws Exception {
        String courseCode = "existing-code";
        Course existingCourse = new Course();
        existingCourse.setStatus(ACTIVE);  // Set status to ACTIVE

        // Mock the repository to return the course with ACTIVE status
        when(courseRepository.findByCode(courseCode)).thenReturn(existingCourse);
        when(courseRepository.save(any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(post("/course/{code}/inactive", courseCode))
                .andExpect(status().isOk());

        verify(courseRepository).save(argThat(course -> course.getStatus().equals(INACTIVE)));
    }
    
}
