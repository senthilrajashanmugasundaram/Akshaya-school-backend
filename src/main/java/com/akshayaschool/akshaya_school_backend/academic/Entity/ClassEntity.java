package com.akshayaschool.akshaya_school_backend.academic.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "class_master",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_class_name_year",
                        columnNames = {"name", "academic_year_id"}
                )
        }
)
@Getter
@Setter
public class ClassEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYearEntity academicYear;

    private Boolean isActive = true;
}

