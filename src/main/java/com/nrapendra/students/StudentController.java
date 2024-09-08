package com.nrapendra.students;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository){
        this.repository=repository;
    }

    @GetMapping(value = "/",consumes = "application/json",produces = "application/json")
    public @ResponseBody ResponseEntity<List<Student>> findAll() {
        var students=repository.findAll();
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Student> findById(@PathVariable("id") Long id) {
        Student student = repository.findById(id).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND,"not found"));
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/create/")
    ResponseEntity<Student> create(@RequestBody Student student) {
        try {
            Student _student = repository.save(student);
            return new ResponseEntity<>(_student, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        repository.findById(id).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,"not found"));
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/")
    ResponseEntity<Student> update(@RequestBody Student student) {
        try {
            repository.findById(student.getId()).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,"not found"));
            repository.save(student);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
