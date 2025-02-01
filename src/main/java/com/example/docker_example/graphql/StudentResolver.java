package com.example.docker_example.graphql;

import com.example.docker_example.models.Student;
import com.example.docker_example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class StudentResolver {

    @Autowired
    private StudentRepository studentRepository;

    @MutationMapping
    public Student registerStudent(@Argument String name, @Argument Integer age, @Argument String address) {
        Student student = new Student();

        student.setName(name);
        student.setAge(age);
        student.setAddress(address);

        return studentRepository.save(student);
    }

    @QueryMapping
    public Optional<Student> getStudent(@Argument Long id) {
        return this.studentRepository.findById(id);
    }
}
