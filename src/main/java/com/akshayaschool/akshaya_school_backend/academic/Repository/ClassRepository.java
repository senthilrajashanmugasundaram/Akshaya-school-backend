package com.akshayaschool.akshaya_school_backend.academic.Repository;

import com.akshayaschool.akshaya_school_backend.academic.Entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {

    boolean existsByNameAndAcademicYearIdAndIsActiveTrue(
            String name,
            Long academicYearId
    );
    Optional<ClassEntity> findByNameAndAcademicYearId(
            String name,
            Long academicYearId
    );

    List<ClassEntity> findByAcademicYearIdAndIsActiveTrue(Long academicYearId);

    List<ClassEntity> findByNameContainingIgnoreCaseAndIsActiveTrue(String name);


}

