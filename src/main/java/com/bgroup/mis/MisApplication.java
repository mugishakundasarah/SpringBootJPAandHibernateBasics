package com.bgroup.mis;

import com.bgroup.mis.model.Department;
import com.bgroup.mis.model.Student;
import com.bgroup.mis.repository.DepartmentRepository;
import com.bgroup.mis.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
@RestController
public class MisApplication {
	public static void main(String[] args) {
		SpringApplication.run(MisApplication.class, args);

	}
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, DepartmentRepository departmentRepository){
		return args -> {
			departmentRepository.saveAll(List.of(new Department("Software Engineering"),new Department("Civil Engineering"),new Department("Welding")));
			Faker faker=new Faker();
			for(int i=0; i<50;i++) {
				String firstName=faker.name().firstName();
				String lastName=faker.name().lastName();
				String email=firstName+"."+lastName+"@gmail.com";
				int day=faker.number().numberBetween(10, 25);
				int month=faker.number().numberBetween(10, 12);
				int years=faker.number().numberBetween(2000, 2010);
				int dp=faker.number().numberBetween(1,3);
				Department dpt=departmentRepository.findById(dp).get();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				String date = day+"/"+month+"/"+years;
				//convert String to LocalDate
				LocalDate dob = LocalDate.parse(date, formatter);
				Student student=new Student(firstName, lastName, email,dob,dpt);
				studentRepository.save(student);
			}
//			Department dp = new Department("software engineer");
//			departmentRepository.save(dp);
//			Department dpt = departmentRepository.findById(1).get();
////			Student student = new Student("Jane", "Hirwa", "jHirwa@gmail.com", LocalDate.now(), dpt);
//			Student student = new Student("Jane", "Hirwa", "jHirwa@gmail.com", LocalDate.now(), dpt);
//			studentRepository.save(student);
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//			String date = "20/02/2005";
//			LocalDate localDate = LocalDate.parse(date, formatter);
//			LocalDate localDate1 = LocalDate.parse("28/01/2006", formatter);
//			Student student = new Student(1L, "Mutoni", "Angel", "angel@gmail.com", localDate1);
//			Student student1 = new Student(2L, "Sandrine", "Keza", "keza@gmail.com", localDate);
//			studentRepository.saveAll(List.of(student, student1));
//			System.out.println("The number of students are " + studentRepository.count());
//
//			studentRepository.findAll().forEach(System.out::println);
//			System.out.println("Finding by Id ");
//			studentRepository.findById(1L).ifPresent(System.out::println);
		};
	}
}
