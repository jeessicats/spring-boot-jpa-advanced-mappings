package com.jeessicats.cruddemo.dao;

import com.jeessicats.cruddemo.entity.Instructor;
import com.jeessicats.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);
}
