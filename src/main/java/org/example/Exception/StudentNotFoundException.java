package org.example.Exception;

public class StudentNotFoundException extends RuntimeException{
    public String customMessage(){
        return "Student Not Found with the given Id!";
    }
}
