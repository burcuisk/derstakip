package com.humtk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kursat on 6.8.2017.
 */

@Entity
@Component
public class Course {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String courseCode;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course", joinColumns = {
            @JoinColumn(name = "course_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "student_id", nullable = false, updatable = false) })
    @JsonManagedReference
    private Set<Student> studentList = new HashSet<Student>(0);;

    @Transient
    private Set<StudentAttendance> studentAttendances = new HashSet<StudentAttendance>(0);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @JsonBackReference
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Set<Student> studentList) {
        this.studentList = studentList;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.course")
    public Set<StudentAttendance> getStudentAttendances() {
        return studentAttendances;
    }

    public void setStudentAttendances(Set<StudentAttendance> studentAttendances) {
        this.studentAttendances = studentAttendances;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
