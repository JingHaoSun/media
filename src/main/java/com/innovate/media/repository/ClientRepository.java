package com.innovate.media.repository;

import com.innovate.media.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ClientRepository extends JpaRepository<Client,Long> {
}
