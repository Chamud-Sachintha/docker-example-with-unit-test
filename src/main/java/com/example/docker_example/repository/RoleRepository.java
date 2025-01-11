package com.example.docker_example.repository;

import com.example.docker_example.models.ERole;
import com.example.docker_example.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<com.example.docker_example.models.Role> findByName(ERole name);
}
