package com.jeessicats.cruddemo.dao;

import com.jeessicats.cruddemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);
}
