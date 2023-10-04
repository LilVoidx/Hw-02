package ru.itis;
import ru.itis.model.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserAPI userAPI = new UserAPI();

        List<User> usersToSave = new ArrayList<>();
        usersToSave.add(new User("user-1", "youssef magdy", 25));
        usersToSave.add(new User("user-2", "magdy anwar", 30));
        usersToSave.add(new User("user-3", "anwar abelfattah", 28));

        userAPI.saveAllUsers(usersToSave);

        System.out.println("Users saved successfully.");

        List<User> allUsers = userAPI.findAllUsers();
        System.out.println("All Users:");
        for (User user : allUsers) {
            System.out.println(user.getUsername() + " - " + user.getFullname() + " - " + user.getAge() + " years old");
        }

        int targetAge = 30;
        List<User> usersByAge = userAPI.findUsersByAge(targetAge);
        System.out.println("\nUsers with age " + targetAge + ":");
        for (User user : usersByAge) {
            System.out.println(user.getUsername() + " - " + user.getFullname() + " - " + user.getAge() + " years old");
        }
    }
}
