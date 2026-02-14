package com.akshayaschool.akshaya_school_backend.academic.service.impl;

import com.akshayaschool.akshaya_school_backend.academic.DTO.AcademicYearRequest;
import com.akshayaschool.akshaya_school_backend.academic.DTO.AcademicYearResponse;
import com.akshayaschool.akshaya_school_backend.academic.Entity.AcademicYearEntity;
import com.akshayaschool.akshaya_school_backend.academic.Repository.AcademicYearRepository;
import com.akshayaschool.akshaya_school_backend.academic.service.AcademicYearService;
import com.akshayaschool.akshaya_school_backend.common.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicYearServiceImpl implements AcademicYearService {

    private final AcademicYearRepository repository;

    @Override
    public AcademicYearResponse create(AcademicYearRequest request) {

        if (repository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Academic Year already exists");
        }

        AcademicYearEntity entity = new AcademicYearEntity();
        entity.setName(request.getName());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());

        AcademicYearEntity saved = repository.save(entity);

        AcademicYearResponse response = new AcademicYearResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setIsActive(saved.getIsActive());

        return response;
    }

    @Override
    public List<AcademicYearResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(e -> {
                    AcademicYearResponse r = new AcademicYearResponse();
                    r.setId(e.getId());
                    r.setName(e.getName());
                    r.setIsActive(e.getIsActive());
                    return r;
                })
                .toList();
    }
}

