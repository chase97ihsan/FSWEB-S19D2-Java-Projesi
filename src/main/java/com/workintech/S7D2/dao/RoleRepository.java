package com.workintech.S7D2.dao;

import com.workintech.S7D2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query("SELECT r FROM Role r WHERE r.authority = :authority")
    Optional<Role> findByAuthority(String authority);
}
