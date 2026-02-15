package com.akshayaschool.akshaya_school_backend.academic.Controller;

import com.akshayaschool.akshaya_school_backend.academic.DTO.ClassRequest;
import com.akshayaschool.akshaya_school_backend.academic.DTO.ClassResponse;
import com.akshayaschool.akshaya_school_backend.academic.service.ClassService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
@Tag(name = "Class Master")
public class ClassController {

    private final ClassService service;

    @PostMapping
    public ResponseEntity<ClassResponse> create(
            @Valid @RequestBody ClassRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ClassResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ClassRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/academic-year/{academicYearId}")
    public ResponseEntity<List<ClassResponse>> getByAcademicYear(
            @PathVariable Long academicYearId) {

        return ResponseEntity.ok(service.getByAcademicYear(academicYearId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClassResponse>> search(
            @RequestParam String keyword) {

        return ResponseEntity.ok(service.search(keyword));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

