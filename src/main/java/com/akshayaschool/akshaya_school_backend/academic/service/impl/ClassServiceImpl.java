package com.akshayaschool.akshaya_school_backend.academic.service.impl;

import com.akshayaschool.akshaya_school_backend.academic.DTO.ClassRequest;
import com.akshayaschool.akshaya_school_backend.academic.DTO.ClassResponse;
import com.akshayaschool.akshaya_school_backend.academic.Entity.AcademicYearEntity;
import com.akshayaschool.akshaya_school_backend.academic.Entity.ClassEntity;
import com.akshayaschool.akshaya_school_backend.academic.Repository.AcademicYearRepository;
import com.akshayaschool.akshaya_school_backend.academic.Repository.ClassRepository;
import com.akshayaschool.akshaya_school_backend.academic.service.ClassService;
import com.akshayaschool.akshaya_school_backend.common.exception.DuplicateResourceException;
import com.akshayaschool.akshaya_school_backend.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final AcademicYearRepository academicYearRepository;

    @Override
    public ClassResponse create(ClassRequest request) {

        AcademicYearEntity year = academicYearRepository.findById(request.getAcademicYearId())
                .orElseThrow(() -> new ResourceNotFoundException("Academic Year not found"));

        if (classRepository.existsByNameAndAcademicYearId(
                request.getName(), request.getAcademicYearId())) {
            throw new DuplicateResourceException("Class already exists for this Academic Year");
        }

        ClassEntity entity = new ClassEntity();
        entity.setName(request.getName());
        entity.setAcademicYear(year);

        return mapToResponse(classRepository.save(entity));
    }

    @Override
    public List<ClassResponse> getAll() {
        return classRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ClassResponse getById(Long id) {
        return classRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
    }

    @Override
    public ClassResponse update(Long id, ClassRequest request) {

        ClassEntity entity = classRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));

        entity.setName(request.getName());

        return mapToResponse(classRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        ClassEntity entity = classRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));

        entity.setIsActive(false); // soft delete
        classRepository.save(entity);
    }

    @Override
    public List<ClassResponse> search(String keyword) {

        return classRepository
                .findByNameContainingIgnoreCaseAndIsActiveTrue(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public List<ClassResponse> getByAcademicYear(Long academicYearId) {

        // Validate Academic Year exists
        if (!academicYearRepository.existsById(academicYearId)) {
            throw new ResourceNotFoundException("Academic Year not found");
        }

        return classRepository
                .findByAcademicYearIdAndIsActiveTrue(academicYearId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ClassResponse mapToResponse(ClassEntity entity) {
        ClassResponse response = new ClassResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setAcademicYearId(entity.getAcademicYear().getId());
        return response;
    }
}
