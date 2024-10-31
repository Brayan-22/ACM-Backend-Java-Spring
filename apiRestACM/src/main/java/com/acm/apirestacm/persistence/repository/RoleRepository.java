package com.acm.apirestacm.persistence.repository;

import com.acm.apirestacm.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
