package com.akshayaschool.akshaya_school_backend.academic.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "class_master",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"name", "academic_year_id"}
                )
        }
)
@Getter
@Setter
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "academic_year_id", nullable = false)
    private Long academicYearId;

    @Column(name = "is_active")
    private Boolean isActive = true;

    private String createdBy;
    private String updatedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

