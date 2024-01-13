package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "dept_name")
    private String deptName;

    @ManyToMany(mappedBy = "departments", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

//    public void addStudent(Student student) {
//
//        this.students.add(student);
//    }
}