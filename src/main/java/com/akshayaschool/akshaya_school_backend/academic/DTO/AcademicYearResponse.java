package com.akshayaschool.akshaya_school_backend.academic.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcademicYearResponse {
    private Long id;
    private String name;
    private Boolean isActive;
}

