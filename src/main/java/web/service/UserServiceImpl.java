package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import web.dao.UserDao;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public class UserServiceImpl implements UserService, UserDetailsService {
private final UserDao userDao;
@Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public void update(int id, User user) {
    userDao.update(id, user);

    }

    @Override
    @Transactional// при использовании транзакшинал, спринг берет на себя ответсвенность за открытие и закрытие сессии
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void save(User user) {
    userDao.save(user);


    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
