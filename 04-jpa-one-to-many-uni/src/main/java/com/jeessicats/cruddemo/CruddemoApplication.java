package com.jeessicats.cruddemo;

import com.jeessicats.cruddemo.dao.AppDAO;
import com.jeessicats.cruddemo.entity.Course;
import com.jeessicats.cruddemo.entity.Instructor;
import com.jeessicats.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

			// createCourseAndReviews(appDAO);

			// retrieveCourseAndReviews(appDAO);

			deleteCourseAndReviews(appDAO);
		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting course with id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course and reviews
		System.out.println("Course: " + tempCourse);
		System.out.println("Reviews: " + tempCourse.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create a course

		Course tempCourse = new Course("Singing - The Basics with Catarina Breeze");

		// add some reviews
		tempCourse.addReview("Great course! Loved it!");
		tempCourse.addReview("The course was very informative.");
		tempCourse.addReview("I didn't like the course.");

		// save the course ... and leverage the cascade all :-)
		System.out.println("Saving course: " + tempCourse);
		System.out.println("The reviews: " + tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting course with id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {

		// get the course
		int theId = 10;

		System.out.println("Finding course with id: " + theId);

		Course tempCourse = appDAO.findCourseById(theId);

		System.out.println("Found course: " + tempCourse);

		// update the course
		System.out.println("Updating course: " + tempCourse);
		tempCourse.setTitle("Singing - Introduction to Harmony");

		appDAO.updateCourse(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		// get the instructor
		int theId = 1;

		System.out.println("Finding instructor with id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Found instructor: " + tempInstructor);

		// update the instructor
		System.out.println("Updating instructor: " + tempInstructor);
		tempInstructor.setLastName("Merryweather");

		appDAO.updateInstructor(tempInstructor);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		// get the instructor
		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor with id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("Found instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void findCoursesByInstructorId(AppDAO appDAO) {
		// find the instructor
		int theId = 1;

		System.out.println("Finding courses for instructor with id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Found instructor: " + tempInstructor);

		// find courses for  instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the courses with the instructor
		tempInstructor.setCourses(courses);

		System.out.println("Found courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		// get the instructor

		int theId = 1;

		System.out.println("Finding instructor with id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Found instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Catarina", "Breeze", "breeze@example.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/breeze", "Singing");

		// associate the instructor with the instructor detail
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course singing = new Course("Singing - The Basics with Catarina Breeze");
		Course singingAdvanced = new Course("Singing - Advanced Techniques with Catarina Breeze");
		Course singingMaster = new Course("Singing - Master Class with Catarina Breeze");

		// add courses to instructor
		tempInstructor.add(singing);
		tempInstructor.add(singingAdvanced);
		tempInstructor.add(singingMaster);

		// save the instructor

		// NOTE: this will also save the courses because of CascadeType.PERSIST

		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Deleting instructor detail with id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// get the instructor detail
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("Instructor detail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("The associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Deleting instructor with id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
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
		Instructor tempInstructor = new Instructor("John", "Olivander", "olivander@example.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/olivander", "Writing");


		// associate the instructor with the instructor detail
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor

		// NOTE: this will also save the details object because of CascadeType.ALL

		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

}
