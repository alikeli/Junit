package org.com.junit.service;

import org.com.junit.dto.User;

import java.util.*;
import java.util.function.Function;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class  UserService {

    private final List<User> users = new ArrayList<>();
    public List<User> getAll() {
        return users;

    }

    public void add(User... users) {
        this.users.addAll(Arrays.asList(users));
    }

    public Optional<User> login(String userName, String password) {
        return users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

    public Map<Integer, User> getAllConvertedById() {

        return users.stream()
                .collect(toMap(User::getId, identity()));
    }
}
