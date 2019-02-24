package com.innovate.media.repository;

import com.innovate.media.domain.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Transactional
    @Modifying
    @Query("update Admin a set a.password = :password where a.id = :id")
    int updateAdminPassword(@Param("id") Long id, @Param("password") String password);

    Admin findAdminByUserNameAndPassword(String user_name, String password);
    @Query(value = "select * from Admin where user_name like %?1%",
    countQuery = "select count(*) from Admin where user_name like %?1%",
    nativeQuery = true)
    Page<Admin> findAllByUserName(String user_name, Pageable pageable);
}
