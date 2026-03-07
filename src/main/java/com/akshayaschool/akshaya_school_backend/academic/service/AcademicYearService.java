package com.akshayaschool.akshaya_school_backend.academic.service;

import com.akshayaschool.akshaya_school_backend.academic.DTO.AcademicYearRequest;
import com.akshayaschool.akshaya_school_backend.academic.DTO.AcademicYearResponse;
import com.akshayaschool.akshaya_school_backend.academic.DTO.ClassResponse;

import java.util.List;

public interface AcademicYearService {

    AcademicYearResponse create(AcademicYearRequest request);

    List<AcademicYearResponse> getAll();

    void delete(Long id);

}

