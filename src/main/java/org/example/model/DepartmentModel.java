package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentModel {
      private String deptName;
      private List<StudentModel> students;
}