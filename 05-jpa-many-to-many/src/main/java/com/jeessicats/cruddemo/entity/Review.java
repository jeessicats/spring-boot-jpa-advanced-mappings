package com.jeessicats.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="review")
public class Review {

    // define the fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="comment")
    private String comment;

    // annotate the class as an entity and map to db table

    // define the fields

    // annotate the fields with db column names

    // create constructors
    public Review() {
    }

    public Review(String comment) {
        this.comment = comment;
    }

    // generate getter/setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // generate toString() method

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
