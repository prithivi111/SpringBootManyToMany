package org.example.Exception;

public class IdNotFoundException extends RuntimeException{
    public String customMessage(){
        return "The input ID is not available in the database! Please input the valid id!";
    }
}
