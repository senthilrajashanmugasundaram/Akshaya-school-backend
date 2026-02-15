package com.akshayaschool.akshaya_school_backend.academic.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "section",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_section_name_class",
                        columnNames = {"name", "class_id"}
                )
        }
)
@Getter
@Setter
public class SectionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private ClassEntity classEntity;

    private Boolean isActive = true;
}

