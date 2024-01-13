package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Student {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stud_id")
    private Integer studId;

    @Column(name = "stud_name")
    private String studName;

    @Column(name = "stud_roll")
    private Integer studRoll;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="stu_dept_table",
               joinColumns = {@JoinColumn(name = "stud_id")},
               inverseJoinColumns =  {@JoinColumn(name = "dept_id")})
    private List<Department> departments;

//    public void addDepartment(Department department) {
//
//        this.departments.add(department);
//    }
}