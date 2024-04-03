package com.example.creswave.springjwt.repository;

import java.util.Optional;


import com.example.creswave.springjwt.models.ERole;
import com.example.creswave.springjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
