package com.crud.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crud.entity.User;

@Service
public class UserServiceImpl implements UserService{

	private static final String FILE_PATH = "users.txt";

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Files.lines(Paths.get(FILE_PATH)).forEach(line -> {
                String[] parts = line.split(",");
                User user = new User();
                user.setId(parts[0]);
                user.setName(parts[1]);
                user.setEmail(parts[2]);
                users.add(user);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(String id) {
        return findAll().stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(user.getId() + "," + user.getName() + "," + user.getEmail());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        List<User> users = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User u : users) {
                if (u.getId().equals(user.getId())) {
                    writer.write(user.getId() + "," + user.getName() + "," + user.getEmail());
                } else {
                    writer.write(u.getId() + "," + u.getName() + "," + u.getEmail());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        List<User> users = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User u : users) {
                if (!u.getId().equals(id)) {
                    writer.write(u.getId() + "," + u.getName() + "," + u.getEmail());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
