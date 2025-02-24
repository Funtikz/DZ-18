package org.example.dz18.service;

import org.example.dz18.exceptions.UserNotFoundException;
import org.example.dz18.models.Address;
import org.example.dz18.models.User;
import org.example.dz18.repo.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository repository;

    @AfterEach
    void afterEach() {
        Mockito.reset(repository);
    }

    @Test
    void findById() throws UserNotFoundException {
        User mockUser = new User(1L, "Василий", "Нарожний", 20,
                new Address(1L, "Гагарина 2", "Волгодонск"));
        when(repository.findById(anyLong())).thenReturn(Optional.of(mockUser));

        String actualUserName = userService.findById(1L).getFirstname();

        assertEquals("Василий", actualUserName);
    }

    @Test
    void findByIdError() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.findById(3L));
        assertEquals("Пользователь с таким id не найден!", exception.getMessage());
    }

    @Test
    void getAll() {
        User mockUser1 = new User(1L, "Василий", "Нарожний", 20,
                new Address(1L, "Гагарина 2", "Волгодонск"));
        User mockUse2 = new User(2L, "Никита", "Нарожний", 30,
                new Address(2L, "Гагарина 2", "Волгодонск"));
        List<User> users = List.of(mockUser1, mockUse2);
        when(repository.findAll()).thenReturn(users);
        List<User> result = userService.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void getAllReturnsEmptyListWhenNoUsers() {
        when(repository.findAll()).thenReturn(Arrays.asList());

        List<User> result = userService.getAll();

        assertEquals(0, result.size());
    }
    @Test
    void createUserReturnsSavedUser() {
        User user = new User(null, "Василий", "Нарожний", 30, null);

        when(repository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals("Василий", result.getFirstname());
        assertEquals("Нарожний", result.getLastname());
        assertEquals(30, result.getAge());
    }

    @Test
    void deleteUserReturnsDeletedUser() throws UserNotFoundException {
        Long userId = 1L;
        User user = new User(userId, "Василий", "Нарожний", 30, null);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.deleteUser(userId);

        assertEquals(user, result);
    }

}