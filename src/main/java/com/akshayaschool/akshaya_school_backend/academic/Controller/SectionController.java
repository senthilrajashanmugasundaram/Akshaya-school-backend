package com.akshayaschool.akshaya_school_backend.academic.Controller;


import com.akshayaschool.akshaya_school_backend.academic.DTO.SectionRequest;
import com.akshayaschool.akshaya_school_backend.academic.DTO.SectionResponse;
import com.akshayaschool.akshaya_school_backend.academic.service.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
@Tag(name = "Section Master")
public class SectionController {

    private final SectionService service;

    @PostMapping
    public ResponseEntity<SectionResponse> create(
            @Valid @RequestBody SectionRequest request) {

        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<SectionResponse>> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<SectionResponse>> search(
            @RequestParam String keyword) {

        return ResponseEntity.ok(service.search(keyword));
    }


    @GetMapping("/{id}")
    public ResponseEntity<SectionResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<SectionResponse>> getByClass(
            @PathVariable Long classId) {

        return ResponseEntity.ok(service.getByClass(classId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SectionResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody SectionRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

