package com.akshayaschool.akshaya_school_backend.academic.Repository;

import com.akshayaschool.akshaya_school_backend.academic.Entity.AcademicYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicYearRepository
        extends JpaRepository<AcademicYearEntity, Long> {

    boolean existsByName(String name);
}


