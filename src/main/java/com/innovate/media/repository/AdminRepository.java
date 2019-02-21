package com.innovate.media.repository;

import com.innovate.media.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Transactional
    @Modifying
    @Query("update Admin a set a.password = :password where a.id = :id")
    int updateAdminPassword(@Param("id") long id, @Param("password") String password);
    Admin findByUserNameAndPassword(String user_name, String password);
}
