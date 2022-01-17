package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void update(int id, User user);

    void removeUser(int id);

    User getUserById(int id);

    List<User> getAllUsers();

    void save(User user);
}

