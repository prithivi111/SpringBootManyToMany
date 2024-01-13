package org.example.repository;


import org.example.entity.Student;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
//    //create custom method which is based on the 2 column Id and name.
//    Optional<Student> findByStudNameAndStudIdAndStudRoll(String name, Integer id, Integer roll);
//
//    @Query("SELECT s FROM Student as s where s.studId=:studId AND s.studName=:studName")
//    Optional<Student> findByIdAndName(Integer studId, String studName);
}