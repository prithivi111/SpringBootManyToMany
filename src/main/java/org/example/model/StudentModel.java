package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class StudentModel {
    private Integer studentId;
    private String studentName;
    private Integer studRoll;
    private List<DepartmentModel> departments;
}