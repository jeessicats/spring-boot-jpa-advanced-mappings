package com.jeessicats.cruddemo.dao;

import com.jeessicats.cruddemo.entity.Course;
import com.jeessicats.cruddemo.entity.Instructor;
import com.jeessicats.cruddemo.entity.InstructorDetail;
import com.jeessicats.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void updateInstructor(Instructor theInstructor);

    void updateCourse(Course theCourse);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCoursesByStudentId(int theId);

    void updateStudent(Student theStudent);

}
