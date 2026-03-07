package com.akshayaschool.akshaya_school_backend.academic.service.impl;

import com.akshayaschool.akshaya_school_backend.academic.DTO.AcademicYearRequest;
import com.akshayaschool.akshaya_school_backend.academic.DTO.AcademicYearResponse;
import com.akshayaschool.akshaya_school_backend.academic.Entity.AcademicYearEntity;
import com.akshayaschool.akshaya_school_backend.academic.Repository.AcademicYearRepository;
import com.akshayaschool.akshaya_school_backend.academic.service.AcademicYearService;
import com.akshayaschool.akshaya_school_backend.common.exception.DuplicateResourceException;
import com.akshayaschool.akshaya_school_backend.common.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AcademicYearServiceImpl
        implements AcademicYearService {

    private final AcademicYearRepository repository;

    @Override
    public AcademicYearResponse create(
            AcademicYearRequest request) {

        Optional<AcademicYearEntity> existing =
                repository.findByName(request.getName());

        if (existing.isPresent()) {

            AcademicYearEntity entity = existing.get();

            if (Boolean.TRUE.equals(entity.getIsActive())) {
                throw new DuplicateResourceException(
                        "Academic Year already exists"
                );
            }

            // 🔥 Restore
            entity.setIsActive(true);
            entity.setUpdatedAt(LocalDateTime.now());
            entity.setUpdatedBy("SYSTEM");

            repository.save(entity);

            return map(entity);
        }

        AcademicYearEntity year = new AcademicYearEntity();
        year.setName(request.getName());
        year.setStartDate(request.getStartDate());
        year.setEndDate(request.getEndDate());
        year.setIsActive(true);
        year.setCreatedAt(LocalDateTime.now());
        year.setUpdatedAt(LocalDateTime.now());
        year.setCreatedBy("SYSTEM");
        year.setUpdatedBy("SYSTEM");

        repository.save(year);

        return map(year);
    }

    @Override
    public List<AcademicYearResponse> getAll() {

        return repository.findByIsActiveTrue()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public void delete(Long id) {

        AcademicYearEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Academic Year not found"));

        entity.setIsActive(false);
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy("SYSTEM");

        repository.save(entity);
    }

    private AcademicYearResponse map(AcademicYearEntity entity) {

        AcademicYearResponse res = new AcademicYearResponse();
        res.setId(entity.getId());
        res.setName(entity.getName());
        res.setStartDate(entity.getStartDate());
        res.setEndDate(entity.getEndDate());

        return res;
    }
}

