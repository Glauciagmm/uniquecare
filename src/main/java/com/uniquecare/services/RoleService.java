package com.uniquecare.services;

import com.uniquecare.models.ERole;
import com.uniquecare.models.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERole name);
}
