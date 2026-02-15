package com.akshayaschool.akshaya_school_backend.academic.service.impl;

import com.akshayaschool.akshaya_school_backend.academic.DTO.SectionRequest;
import com.akshayaschool.akshaya_school_backend.academic.DTO.SectionResponse;
import com.akshayaschool.akshaya_school_backend.academic.Entity.ClassEntity;
import com.akshayaschool.akshaya_school_backend.academic.Entity.SectionEntity;
import com.akshayaschool.akshaya_school_backend.academic.Repository.ClassRepository;
import com.akshayaschool.akshaya_school_backend.academic.Repository.SectionRepository;
import com.akshayaschool.akshaya_school_backend.academic.service.SectionService;
import com.akshayaschool.akshaya_school_backend.common.exception.DuplicateResourceException;
import com.akshayaschool.akshaya_school_backend.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final ClassRepository classRepository;

    @Override
    public SectionResponse create(SectionRequest request) {

        ClassEntity classEntity = classRepository.findById(request.getClassId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Class not found"));

        if (sectionRepository.existsByNameAndClassEntityId(
                request.getName(), request.getClassId())) {
            throw new DuplicateResourceException(
                    "Section already exists for this class");
        }

        SectionEntity entity = new SectionEntity();
        entity.setName(request.getName());
        entity.setClassEntity(classEntity);

        return mapToResponse(sectionRepository.save(entity));
    }

    @Override
    public List<SectionResponse> getAll() {

        return sectionRepository.findAll()
                .stream()
                .filter(SectionEntity::getIsActive)
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public SectionResponse getById(Long id) {

        SectionEntity entity = sectionRepository.findById(id)
                .filter(SectionEntity::getIsActive)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Section not found"));

        return mapToResponse(entity);
    }

    @Override
    public SectionResponse update(Long id, SectionRequest request) {

        SectionEntity entity = sectionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Section not found"));

        if (!entity.getName().equals(request.getName())
                && sectionRepository.existsByNameAndClassEntityId(
                request.getName(), entity.getClassEntity().getId())) {

            throw new DuplicateResourceException(
                    "Section already exists for this class");
        }

        entity.setName(request.getName());

        return mapToResponse(sectionRepository.save(entity));
    }

    @Override
    public void delete(Long id) {

        SectionEntity entity = sectionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Section not found"));

        entity.setIsActive(false); // soft delete
        sectionRepository.save(entity);
    }

    @Override
    public List<SectionResponse> search(String keyword) {

        return sectionRepository
                .findByNameContainingIgnoreCaseAndIsActiveTrue(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public List<SectionResponse> getByClass(Long classId) {

        if (!classRepository.existsById(classId)) {
            throw new ResourceNotFoundException("Class not found");
        }

        return sectionRepository
                .findByClassEntityIdAndIsActiveTrue(classId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    private SectionResponse mapToResponse(SectionEntity entity) {

        SectionResponse response = new SectionResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setClassId(entity.getClassEntity().getId());

        return response;
    }
}

