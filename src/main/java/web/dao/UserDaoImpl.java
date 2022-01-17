package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User user) {
        User userToBeUpdated = entityManager.find(User.class, id);
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setEmail(user.getEmail());
        entityManager.merge(user);

    }

    @Override
    public void removeUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUserById(int id) {

        return entityManager.find(User.class, id);

    }

    @Override
    public List<User> getAllUsers() {// урок 070
        List<User> userList = entityManager.createQuery("select user from User user",
                User.class).getResultList();
        return userList;
    }
}
