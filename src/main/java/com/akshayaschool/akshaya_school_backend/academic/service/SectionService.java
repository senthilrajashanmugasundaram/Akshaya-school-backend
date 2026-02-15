package com.akshayaschool.akshaya_school_backend.academic.service;

import com.akshayaschool.akshaya_school_backend.academic.DTO.SectionRequest;
import com.akshayaschool.akshaya_school_backend.academic.DTO.SectionResponse;

import java.util.List;

public interface SectionService {

    SectionResponse create(SectionRequest request);

    List<SectionResponse> getAll();

    SectionResponse getById(Long id);

    SectionResponse update(Long id, SectionRequest request);

    void delete(Long id);

    List<SectionResponse> getByClass(Long classId);

    List<SectionResponse> search(String keyword);


}

