package com.jeessicats.cruddemo.dao;

import com.jeessicats.cruddemo.entity.Course;
import com.jeessicats.cruddemo.entity.Instructor;
import com.jeessicats.cruddemo.entity.InstructorDetail;
import com.jeessicats.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    // define the fields for entity manager
    private EntityManager entityManager;

    // inject the entity manager using constructor injection
    public AppDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        //retrieve the instructor using the primary key
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        // retrieve the instructor detail using the primary key
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // get the courses
        List<Course> courses = tempInstructorDetail.getInstructor().getCourses();

        // break association of all courses with this instructor
        for (Course course : courses) {
            course.setInstructor(null);
        }

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // create query
        TypedQuery<Course> theQuery = entityManager.createQuery("from Course where instructor.id = :data", Course.class);

        // set parameter
        theQuery.setParameter("data", theId);

        // execute query
        List<Course> courses = theQuery.getResultList();

        // return the results
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // create query
        TypedQuery<Instructor> theQuery = entityManager.createQuery("select i from Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail where i.id = :data", Instructor.class);

        // set parameter
        theQuery.setParameter("data", theId);

        // execute query
        Instructor instructor = theQuery.getSingleResult();

        // return the results
        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor theInstructor) {
        entityManager.merge(theInstructor);

    }

    @Override
    @Transactional
    public void updateCourse(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse = entityManager.find(Course.class, theId);

        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        // create query
        TypedQuery<Course> theQuery = entityManager.createQuery("select c from Course c JOIN FETCH c.reviews where c.id = :data", Course.class);

        // execute query
        theQuery.setParameter("data", theId);
        Course course = theQuery.getSingleResult();

        // return the results
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        // create query
        TypedQuery<Course> theQuery = entityManager.createQuery("select c from Course c JOIN FETCH c.students where c.id = :data", Course.class);

        // execute query
        theQuery.setParameter("data", theId);

        Course course = theQuery.getSingleResult();

        // return the results
        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("select s from Student s JOIN FETCH s.courses where s.id = :data", Student.class);

        // execute query
        theQuery.setParameter("data", theId);

        Student student = theQuery.getSingleResult();

        // return the results
        return student;
    }

    @Override
    @Transactional
    public void updateStudent(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student tempStudent = entityManager.find(Student.class, theId);

        if (tempStudent != null) {
            List<Course> courses = tempStudent.getCourses();
            for (Course course : courses) {
                course.getStudents().remove(tempStudent);
            }

            entityManager.remove(tempStudent);
        }
    }
}
