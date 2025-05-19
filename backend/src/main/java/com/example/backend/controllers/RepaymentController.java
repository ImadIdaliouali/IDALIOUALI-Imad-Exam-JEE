package com.example.backend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.example.backend.dtos.RepaymentDTO;
import com.example.backend.enums.RepaymentType;
import com.example.backend.services.RepaymentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/repayments")
@RequiredArgsConstructor
@Tag(name = "Repayment Management", description = "Operations pertaining to repayment management")
@CrossOrigin("*")
public class RepaymentController {
    private final RepaymentService repaymentService;

    @GetMapping
    @Operation(summary = "Get all repayments", description = "Retrieves a list of all repayments")
    public ResponseEntity<List<RepaymentDTO>> getAllRepayments() {
        return ResponseEntity.ok(repaymentService.getAllRepayments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a repayment by ID", description = "Retrieves a repayment by its ID")
    public ResponseEntity<RepaymentDTO> getRepaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(repaymentService.getRepaymentById(id));
    }

    @GetMapping("/credit/{creditId}")
    @Operation(summary = "Get repayments by credit ID", description = "Retrieves repayments for a specific credit")
    public ResponseEntity<List<RepaymentDTO>> getRepaymentsByCreditId(@PathVariable Long creditId) {
        return ResponseEntity.ok(repaymentService.getRepaymentsByCreditId(creditId));
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Get repayments by type", description = "Retrieves repayments of a specific type")
    public ResponseEntity<List<RepaymentDTO>> getRepaymentsByType(@PathVariable RepaymentType type) {
        return ResponseEntity.ok(repaymentService.getRepaymentsByType(type));
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get repayments by date range", description = "Retrieves repayments within a specific date range")
    public ResponseEntity<List<RepaymentDTO>> getRepaymentsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return ResponseEntity.ok(repaymentService.getRepaymentsByDateRange(startDate, endDate));
    }

    @PostMapping
    @Operation(summary = "Create a new repayment", description = "Creates a new repayment with the provided details")
    public ResponseEntity<RepaymentDTO> createRepayment(@RequestBody RepaymentDTO repaymentDTO) {
        return new ResponseEntity<>(repaymentService.saveRepayment(repaymentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a repayment", description = "Updates an existing repayment with the provided details")
    public ResponseEntity<RepaymentDTO> updateRepayment(@PathVariable Long id, @RequestBody RepaymentDTO repaymentDTO) {
        return ResponseEntity.ok(repaymentService.updateRepayment(id, repaymentDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a repayment", description = "Deletes a repayment with the specified ID")
    public ResponseEntity<Void> deleteRepayment(@PathVariable Long id) {
        repaymentService.deleteRepayment(id);
        return ResponseEntity.noContent().build();
    }
}
