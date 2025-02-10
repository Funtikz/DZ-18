package org.example.dz18.controller;

import lombok.RequiredArgsConstructor;
import org.example.dz18.exceptions.UserNotFoundException;
import org.example.dz18.models.User;
import org.example.dz18.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/find-all")
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) throws UserNotFoundException {
        return  new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user){
        return  new ResponseEntity<>(service.createUser(user), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(service.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
    }

}
