package com.akshayaschool.akshaya_school_backend.academic.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long academicYearId;
}

