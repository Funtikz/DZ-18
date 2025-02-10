package org.example.dz18.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String message){
        super(message);
    }

}
