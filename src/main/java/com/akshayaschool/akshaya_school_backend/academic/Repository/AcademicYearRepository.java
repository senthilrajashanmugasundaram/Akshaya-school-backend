package com.akshayaschool.akshaya_school_backend.academic.Repository;

import com.akshayaschool.akshaya_school_backend.academic.Entity.AcademicYearEntity;
import com.akshayaschool.akshaya_school_backend.academic.Entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AcademicYearRepository
        extends JpaRepository<AcademicYearEntity, Long> {

    boolean existsByName(String name);

        Optional<AcademicYearEntity> findByName(String name);

    List<AcademicYearEntity> findByIsActiveTrue();


}


