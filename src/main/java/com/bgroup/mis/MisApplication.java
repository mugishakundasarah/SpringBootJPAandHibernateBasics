package com.bgroup.mis;

import com.bgroup.mis.model.Student;
import com.bgroup.mis.repository.StudentRepository;
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
	CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return args -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String date = "20/02/2005";
			LocalDate localDate = LocalDate.parse(date, formatter);
			LocalDate localDate1 = LocalDate.parse("28/01/2006", formatter);
			Student student = new Student(1L, "Mutoni", "Angel", "angel@gmail.com", localDate1);
			Student student1 = new Student(2L, "Sandrine", "Keza", "keza@gmail.com", localDate);
			studentRepository.saveAll(List.of(student, student1));
			System.out.println("The number of students are " + studentRepository.count());

			studentRepository.findAll().forEach(System.out::println);
			System.out.println("Finding by Id ");
			studentRepository.findById(1L).ifPresent(System.out::println);
		};
	}
}
