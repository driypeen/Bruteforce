package com.driypeen.users.repository;

import com.driypeen.users.model.Role;
import com.driypeen.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}

