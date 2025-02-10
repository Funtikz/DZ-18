package org.example.dz18.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.dz18.exceptions.UserNotFoundException;
import org.example.dz18.models.Address;
import org.example.dz18.models.User;
import org.example.dz18.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Transactional
    public User findById(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь с таким id не найден!"));
    }

    @Transactional
    public List<User> getAll(){
        return repository.findAll();
    }
    @Transactional
    public User createUser(User user){
        return repository.save(user);
    }
    @Transactional
    public User updateUser(Long id, User user) throws UserNotFoundException {
        User byId = findById(id);
        user.getAddress().setId(byId.getAddress().getId());
        byId.setFirstname(user.getFirstname());
        byId.setLastname(user.getLastname());
        byId.setAge(user.getAge());
        if (!(byId.getAddress().equals(user.getAddress()))){
            byId.setAddress(user.getAddress());
        }
        repository.save(byId);
        return byId;
    }
    @Transactional
    public User deleteUser(Long id) throws UserNotFoundException {
        User user = findById(id);
        repository.delete(user);
        return user;
    }

}
