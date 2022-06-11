package com.driypeen.users.repository;

import com.driypeen.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
    List<User> findAll();
    User save(User user);
}
