package com.jeessicats.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="course")
public class Course {
    // annotate the class as an entity and map to db table

    // define the fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="course_id")
    private List<Review> reviews;

    // annotate the fields with db column names

    // create constructors

    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }

    // generate getter/setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // add a convenience method
    public void addReview(String theReview) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(new Review(theReview));
    }

    // generate toString() method
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
