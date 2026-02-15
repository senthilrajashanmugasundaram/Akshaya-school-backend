package com.akshayaschool.akshaya_school_backend.academic.Repository;

import com.akshayaschool.akshaya_school_backend.academic.Entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {

    boolean existsByNameAndAcademicYearId(String name, Long academicYearId);

    List<ClassEntity> findByAcademicYearIdAndIsActiveTrue(Long academicYearId);

    List<ClassEntity> findByNameContainingIgnoreCaseAndIsActiveTrue(String name);


}

