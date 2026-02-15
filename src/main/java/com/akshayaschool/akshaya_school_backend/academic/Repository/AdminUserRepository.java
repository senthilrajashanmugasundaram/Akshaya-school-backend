package com.akshayaschool.akshaya_school_backend.academic.Repository;

import com.akshayaschool.akshaya_school_backend.academic.Entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRepository
        extends JpaRepository<AdminUser, Long> {

    Optional<AdminUser> findByUsername(String username);
}

