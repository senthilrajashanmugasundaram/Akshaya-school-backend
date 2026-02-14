package com.akshayaschool.akshaya_school_backend.academic.Controller;

import com.akshayaschool.akshaya_school_backend.academic.DTO.AcademicYearRequest;
import com.akshayaschool.akshaya_school_backend.academic.DTO.AcademicYearResponse;
import com.akshayaschool.akshaya_school_backend.academic.Entity.AcademicYearEntity;
import com.akshayaschool.akshaya_school_backend.academic.Repository.AcademicYearRepository;
import com.akshayaschool.akshaya_school_backend.academic.service.AcademicYearService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/academic-years")
@RequiredArgsConstructor
@Tag(name = "Academic Master")
public class AcademicYearController {

    private final AcademicYearService service;

    @PostMapping
    public ResponseEntity<AcademicYearResponse> create(
            @Valid @RequestBody AcademicYearRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<AcademicYearResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}

