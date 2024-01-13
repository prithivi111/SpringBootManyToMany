package org.example.service;

import org.example.Exception.IdNotFoundException;
import org.example.Exception.StudentNotFoundException;
import org.example.entity.Department;
import org.example.entity.Student;
//import org.example.exception.StudentNotFoundException;
import org.example.model.DepartmentModel;
import org.example.model.StudentModel;
import org.example.repository.DepartmentRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    public String saveAllStudent(List<StudentModel> studentModels) {
        //populate entity object from model
       // List<Student> students = new ArrayList<>();

        for (StudentModel studentModel : studentModels) {
            Student student = new Student();
            student.setStudId(studentModel.getStudentId());
            student.setStudName(studentModel.getStudentName());
            student.setStudRoll(studentModel.getStudRoll());

            List<Department> departments = new ArrayList<>();

            for (DepartmentModel departmentModel : studentModel.getDepartments()) {
                Department department = new Department();

                //Yeha department set garnu bhanda agadi, tyo department DB ko table ma cha ki chaina check garnu paryo ni
                //Yedi cha bhane set nagarne, chaina bhane matra set garne!!
               Optional<Department> departmentOps = this.departmentRepository.findByDeptName(departmentModel.getDeptName());
                if(departmentOps.isPresent()){
                    department = departmentOps.get();
                } else{
                    department.setDeptName(departmentModel.getDeptName());
                }
                departments.add(department);
            }
            student.setDepartments(departments);
            studentRepository.save(student);
         //  students.add(student);
        }
        //studentRepository.saveAll(students);  //Yeha sabai students ko record accumulate garera saveAll garna mildaina kinaki
                                                //hamilai department cha ki chaina bhanera mathi check garnu parne huncah ra chaina bhane
                                               //populate garnu parne huncha. tei bhayera individually save garnu parcha!
        return "Success";
    }

    public List<StudentModel> fetchAllStudData() {

        List<StudentModel> studentModels = new ArrayList<>();

        List<Student> studentList = null;
       try {
           studentList = (List<Student>) this.studentRepository.findAll();
       } catch(Exception e){
           System.out.println("Exception identified...");
       }
        if (!studentList.isEmpty()) {
            for (Student student : studentList) {
                //populate model from entity
                StudentModel studentModel = new StudentModel();
                studentModel.setStudentId(student.getStudId());
                studentModel.setStudentName(student.getStudName());
                studentModel.setStudRoll(student.getStudRoll());

                List<DepartmentModel> departmentModels = new ArrayList<DepartmentModel>();
                for (Department department : student.getDepartments()) {
                    DepartmentModel departmentModel = new DepartmentModel();
                    departmentModel.setDeptName(department.getDeptName());

                    departmentModels.add(departmentModel);
                }

                studentModel.setDepartments(departmentModels);
                studentModels.add(studentModel);
            }
        }
        return studentModels;
    }

    public StudentModel fetchStudData(Integer studId) {
        StudentModel studentModel = new StudentModel();
        Student student = new Student();
        Optional<Student> optionalStudent = this.studentRepository.findById(studId);//yeha Optional 'null' haina 'empty' huncha, be aware about that!
            if(optionalStudent.isEmpty()){
                throw new IdNotFoundException(); // I used the exception here
            } else {
                student = optionalStudent.get();
            }
        if (student != null) {
            studentModel.setStudentId(student.getStudId());
            studentModel.setStudentName(student.getStudName());
            studentModel.setStudRoll(student.getStudRoll());

            List<DepartmentModel> departmentModels = new ArrayList<DepartmentModel>();
            for (Department department : student.getDepartments()) {
                DepartmentModel departmentModel = new DepartmentModel();
                departmentModel.setDeptName(department.getDeptName());

                departmentModels.add(departmentModel);
            }
            studentModel.setDepartments(departmentModels);

        }
        return studentModel;
    }

    public String updateStudName(Integer studId, StudentModel studentModel) {
        Student student = new Student();
        try {
            Optional<Student> studentOptional = this.studentRepository.findById(studId);
            if (studentOptional.isPresent()) {
                student = studentOptional.get();
            }
        } catch (Exception e) {
           System.err.println("Exception identified .. " + e.getMessage());
           return "Invalid Student Id";

        }
        if (student.getStudId() != null) {
            //student.setStudName("GANESH");

            student.setStudId(studentModel.getStudentId());
            student.setStudName(studentModel.getStudentName());
            student.setStudRoll(studentModel.getStudRoll());

            List<Department> departments = new ArrayList<>();

            for (DepartmentModel departmentModel : studentModel.getDepartments()) {
                Department department = new Department();
                department.setDeptName(departmentModel.getDeptName());
                departments.add(department);
            }

            student.setDepartments(departments);
            this.studentRepository.save(student);
            return "Updated Successfully.";
        } else {
            throw new StudentNotFoundException();
        }
    }


}

//    public StudentModel fetchStudentInfo(Integer studId, String studName) {
//        StudentModel studentModel = new StudentModel();
//        Student student = null;
//        try {
//            //Optional<Student> studentOpsObj = this.studentRepository.findByStudIdAndStudName(studId, studName);
//            Optional<Student> studentOpsObj  = this.studentRepository.findByIdAndName(studId, studName);
//            if (studentOpsObj.isPresent()) {
//                student = studentOpsObj.get();
//            }
//        } catch (Exception e) {
//            System.err.println("Exception identified .. " + e.getMessage());
//            return new StudentModel();
//        }
//        if (student != null) {
//            studentModel.setStudentId(student.getStudId());
//            studentModel.setStudentName(student.getStudName());
//            studentModel.setStudRoll(student.getStudRoll());
//        }
//        return studentModel;
//    }
//

//    public String deleteStudentData(Integer studId) {
//        try {
//            this.studentRepository.deleteById(studId);
//            return "Data deleted Successfully";
//        } catch (Exception e) {
//            System.err.println("Exception identified .. " + e.getMessage());
//            return "Couldn't perform Operations";
//        }
//    }
