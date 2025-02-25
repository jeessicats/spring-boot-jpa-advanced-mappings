package com.jeessicats.cruddemo;

import com.jeessicats.cruddemo.dao.AppDAO;
import com.jeessicats.cruddemo.entity.Instructor;
import com.jeessicats.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			// createInstructor(appDAO);

			findInstructor(appDAO);
		};
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor with id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Found instructor: " + tempInstructor);

		System.out.println("The associated instructor detail: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		/*
		// create the instructor
		Instructor tempInstructor = new Instructor("Olivia", "Willow", "olivia@example.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/olivia", "Reading");
		 */

		// create the instructor
		Instructor tempInstructor = new Instructor("Oliver", "Potter", "olivia@example.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/Oliver", "Writing");


		// associate the instructor with the instructor detail
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor

		// NOTE: this will also save the details object because of CascadeType.ALL

		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

}
