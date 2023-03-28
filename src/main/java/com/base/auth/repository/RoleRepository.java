package com.base.auth.repository;

import com.base.auth.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findRoleByName(String name);
}
