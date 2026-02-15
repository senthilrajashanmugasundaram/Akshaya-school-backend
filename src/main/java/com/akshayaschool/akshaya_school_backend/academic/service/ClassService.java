package com.akshayaschool.akshaya_school_backend.academic.service;

import com.akshayaschool.akshaya_school_backend.academic.DTO.ClassRequest;
import com.akshayaschool.akshaya_school_backend.academic.DTO.ClassResponse;

import java.util.List;

public interface ClassService {

    ClassResponse create(ClassRequest request);

    List<ClassResponse> getAll();

    ClassResponse getById(Long id);

    ClassResponse update(Long id, ClassRequest request);

    void delete(Long id);

    List<ClassResponse> getByAcademicYear(Long academicYearId);

    List<ClassResponse> search(String keyword);


}

