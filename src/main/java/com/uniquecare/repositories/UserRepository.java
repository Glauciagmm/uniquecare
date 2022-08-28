package com.uniquecare.repositories;

import com.uniquecare.models.Facility;
import com.uniquecare.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long userId);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User getByUsername (String username);

    List<Facility> findFacilitiesByAssistantCity(String city);
}
