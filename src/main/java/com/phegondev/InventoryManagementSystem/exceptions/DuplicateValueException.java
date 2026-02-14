package com.phegondev.InventoryManagementSystem.exceptions;

public class DuplicateValueException  extends RuntimeException{
    public DuplicateValueException(String message){
        super(message);
    }
}
