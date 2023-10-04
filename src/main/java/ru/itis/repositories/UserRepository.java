package ru.itis.repositories;
import ru.itis.model.User;
import java.util.List;

public interface UserRepository {
    void saveAll(List<User> users);
    List<User> findAll();
    List<User> findByAge(int age);
}
