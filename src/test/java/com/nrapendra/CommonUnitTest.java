package com.nrapendra;

import com.nrapendra.students.Student;

import java.util.ArrayList;
import java.util.List;

public class CommonUnitTest {

    public List<Student> studentList(){
        var firstStudent = new Student();

        firstStudent.setEmail("first@example.com");
        firstStudent.setFirstName("first");
        firstStudent.setLastName("first_lastname");
        firstStudent.setId(1L);

        var secondStudent = new Student();
        secondStudent.setEmail("second@example.com");
        secondStudent.setFirstName("second");
        secondStudent.setLastName("second_lastname");
        secondStudent.setId(2L);

        return new ArrayList<>(List.of(firstStudent, secondStudent));
    }

}
