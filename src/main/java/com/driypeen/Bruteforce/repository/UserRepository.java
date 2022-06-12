package com.driypeen.Bruteforce.repository;

import com.driypeen.Bruteforce.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);
    List<User> findAll();
    User save(User user);
}
