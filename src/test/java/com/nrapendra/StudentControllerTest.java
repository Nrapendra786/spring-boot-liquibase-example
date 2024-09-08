package com.nrapendra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrapendra.students.Student;
import com.nrapendra.students.StudentController;
import com.nrapendra.students.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest extends CommonUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository repository;

    @Test
    public void testGetAllStudents() throws Exception {
        //when
        when(repository.findAll()).thenReturn(studentList());

        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("first"))
                .andExpect(jsonPath("$[0].lastName").value("first_lastname"))
                .andExpect(jsonPath("$[0].email").value("first@example.com"))
                .andExpect(jsonPath("$[1].firstName").value("second"))
                .andExpect(jsonPath("$[1].lastName").value("second_lastname"))
                .andExpect(jsonPath("$[1].email").value("second@example.com"));
    }

    @Test
    public void testGetStudentById() throws Exception {
        //when
        when(repository.findById(1L)).thenReturn(studentList().stream().findFirst());

        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("first"))
                .andExpect(jsonPath("$.lastName").value("first_lastname"))
                .andExpect(jsonPath("$.email").value("first@example.com"));
        ;
    }

    @Test
    public void testCreateStudent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/students/create/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString( new Student(1L,"John","Singh","ajay@example.com")))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andDo(print());
    }

    @Test
    public void testUpdateStudent() throws Exception {

        when(repository.findById(1L)).thenReturn(studentList().stream().findFirst());

        mockMvc.perform(put("/students/update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString( new Student(1L,"John","Singh","ajay@example.com")))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.email").value("ajay@example.com"));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        //when
        when(repository.findById(1L)).thenReturn(studentList().stream().findFirst());

        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/students/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
