package org.example.repository;

import org.example.entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    Optional<Department> findByDeptName(String deptName);

}
