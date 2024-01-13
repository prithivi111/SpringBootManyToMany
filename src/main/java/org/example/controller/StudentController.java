package org.example.controller;


import org.example.model.StudentModel;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/saveAllStudent")
    public ResponseEntity<String> saveAllStudent(@RequestBody List<StudentModel> studentModels) {
        String receivedResponse = this.studentService.saveAllStudent(studentModels);

        if (receivedResponse.equalsIgnoreCase("Success")) {
            return new ResponseEntity<>("Successfully Saved Data..", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not Saved Data..", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fetchAllStudData")
    public ResponseEntity<List<StudentModel>> fetchAllStudData() {
        List<StudentModel> studentModels = null;
        studentModels= this.studentService.fetchAllStudData();
        if (studentModels != null && !studentModels.isEmpty()) {
            return new ResponseEntity<>(studentModels, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/fetchStudData/{studentId}")
    public ResponseEntity<StudentModel> fetchStudData(@PathVariable("studentId") Integer studentId) {
        System.out.println("Received Student Id : " + studentId);
        StudentModel studentModel = null;
        studentModel = this.studentService.fetchStudData(studentId);
        return new ResponseEntity<>(studentModel, HttpStatus.OK);
    }

    @PutMapping("/updateStudName")
    public ResponseEntity<String> updateStudName(@RequestParam("studentId") Integer studentId, @RequestBody StudentModel studentModel) {
        String receivedData = this.studentService.updateStudName(studentId, studentModel);
        return new ResponseEntity<>(receivedData, HttpStatus.OK);
    }
}

//    @GetMapping("/fetchStudInfo/{id}/{name}")
//    public StudentModel fetchStudInfo(@PathVariable Integer id, @PathVariable String name, @RequestParam("stdId") Integer studId) {  // http://localhost:8083/springBasic/student/fetchStudInfo/14/DFG?stdId=14
//        System.out.println("Received Student Id : "+id+ " :::  Student name ::" +name);
//        StudentModel studentModel = this.studentService.fetchStudentInfo(id, name);
//        return studentModel;
//    }
//
//
//
//    @DeleteMapping("/deleteStudData/{studId}")
//    public ResponseEntity<String> deleteStudData(@PathVariable Integer studId) {
//        return new ResponseEntity<>(this.studentService.deleteStudentData(studId), HttpStatus.OK);

