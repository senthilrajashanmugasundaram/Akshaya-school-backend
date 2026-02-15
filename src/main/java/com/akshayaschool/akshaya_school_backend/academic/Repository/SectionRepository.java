package com.akshayaschool.akshaya_school_backend.academic.Repository;

import com.akshayaschool.akshaya_school_backend.academic.Entity.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

    boolean existsByNameAndClassEntityId(String name, Long classId);

    List<SectionEntity> findByClassEntityIdAndIsActiveTrue(Long classId);

    List<SectionEntity> findByNameContainingIgnoreCaseAndIsActiveTrue(String name);


}

