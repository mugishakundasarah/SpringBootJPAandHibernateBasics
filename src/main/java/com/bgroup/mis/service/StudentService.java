package com.bgroup.mis.service;

import com.bgroup.mis.model.Student;
import com.bgroup.mis.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public List<Student> listStudents(){
        return studentRepository.findAll();
    }
    public void addStudent(Student student){
        studentRepository.save(student);
    }
    public Student getStudentById(Long id){
        return studentRepository.findById(id).get();
    }

    public void deleteStudent(Student student){
        studentRepository.delete(student);
    }

    public Student getStudentByEmail(String email){
        return studentRepository.findByEmail(email).get();
    }
    public List<Student> getByLastName(String lastName){
        return studentRepository.findByLastName(lastName);
    }

    public Page<Student> getAllStudent(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return studentRepository.findAll(paging);
    }
}
