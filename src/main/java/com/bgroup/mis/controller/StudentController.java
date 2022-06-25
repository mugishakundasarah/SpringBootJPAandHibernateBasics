package com.bgroup.mis.controller;

import com.bgroup.mis.model.Student;
import com.bgroup.mis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:8082")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/app/students")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> listAllStudents(){
        return studentService.listStudents();
    }

    @PostMapping("/app/students")
    @ResponseStatus(HttpStatus.OK)
    public void addNewStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }
    @GetMapping("/app/students/{studentid}")
    @ResponseStatus(HttpStatus.OK)
    public Student findStudentById(@PathVariable("studentid") Long id){
        return studentService.getStudentById(id);
    }

    @PutMapping("/app/students/{studentid}")
    public void updateStudent(@RequestBody Student student, @PathVariable("studentid") Long id){
      Student s = studentService.getStudentById(id);
      s.setFirstName(student.getFirstName());
      s.setLastName(student.getLastName());
      studentService.addStudent(s);
    }

    @DeleteMapping("/app/students/{studentid}")
    public void deleteStudent(@PathVariable("studentid") Long id){
        studentService.deleteStudent(studentService.getStudentById(id));
    }

    @GetMapping("/app/students/finder")
    public Student getStudentByEmail(@RequestParam("studentEmail") String email){
        return studentService.getStudentByEmail(email);
    }

    @GetMapping("/app/students/list")
    public List<Student> getStudentByLname(@RequestParam("'studentLastName") String studentLastName){
        return studentService.getByLastName(studentLastName);
    }

    /* Sample calling
     * http://localhost:8080/students/pages/?pageSize=10&pageNo=2&sortBy=email
     * http://localhost:8080/students/pages/?pageSize=50&pageNo=1&sortBy=email
     * http://localhost:8080/students/pages/?pageSize=10&pageNo=3&sortBy=firstName
     */
    @GetMapping("/app/students/pages")
    @ResponseStatus(HttpStatus.OK)
    public Page<Student> getPages(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        return studentService.getAllStudent(pageNo, pageSize, sortBy);

    }
}
