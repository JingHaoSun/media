package com.innovate.media.repository;

import com.innovate.media.domain.WorkCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkCategoryRepository extends JpaRepository<WorkCategory, Long> {
}
