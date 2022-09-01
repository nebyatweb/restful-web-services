package com.nebyatweb.rest.webservices.restfulwebservices.service;

import com.nebyatweb.rest.webservices.restfulwebservices.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();
    private static int userId = 0;

    static {
        users.add(new User(++userId, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userId, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++userId, "Jim", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    public User addUser(User user) {
        user.setId(++userId);
        users.add(user);
        return user;
    }
}
