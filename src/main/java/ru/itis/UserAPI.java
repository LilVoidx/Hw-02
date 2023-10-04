package ru.itis;
import ru.itis.model.User;
import ru.itis.repositories.UserRepository;
import ru.itis.repositories.UserRepositoryImpl;

import java.util.List;

public class UserAPI {
    private UserRepository userRepository;

    public UserAPI() {

        userRepository = new UserRepositoryImpl();
    }

    public void saveAllUsers(List<User> users) {
        userRepository.saveAll(users);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findUsersByAge(int age) {
        return userRepository.findByAge(age);
    }
}
