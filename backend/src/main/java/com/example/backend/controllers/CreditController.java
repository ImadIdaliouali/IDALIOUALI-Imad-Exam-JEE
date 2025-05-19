package com.example.backend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.example.backend.dtos.CreditDTO;
import com.example.backend.dtos.PersonalCreditDTO;
import com.example.backend.dtos.ProfessionalCreditDTO;
import com.example.backend.dtos.RealEstateCreditDTO;
import com.example.backend.enums.CreditStatus;
import com.example.backend.services.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
@Tag(name = "Credit Management", description = "Operations pertaining to credit management")
@CrossOrigin("*")
public class CreditController {
    private final CreditService creditService;

    // General credit endpoints
    @GetMapping
    @Operation(summary = "Get all credits", description = "Retrieves a list of all credits")
    public ResponseEntity<List<CreditDTO>> getAllCredits() {
        return ResponseEntity.ok(creditService.getAllCredits());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a credit by ID", description = "Retrieves a credit by its ID")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getCreditById(id));
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get credits by client ID", description = "Retrieves credits for a specific client")
    public ResponseEntity<List<CreditDTO>> getCreditsByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.getCreditsByClientId(clientId));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get credits by status", description = "Retrieves credits with a specific status")
    public ResponseEntity<List<CreditDTO>> getCreditsByStatus(@PathVariable CreditStatus status) {
        return ResponseEntity.ok(creditService.getCreditsByStatus(status));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a credit", description = "Deletes a credit with the specified ID")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }

    // Credit status management
    @PutMapping("/{id}/status")
    @Operation(summary = "Update credit status", description = "Updates the status of a credit")
    public ResponseEntity<CreditDTO> updateCreditStatus(@PathVariable Long id, @RequestParam CreditStatus status) {
        return ResponseEntity.ok(creditService.updateCreditStatus(id, status));
    }

    @PutMapping("/{id}/accept")
    @Operation(summary = "Accept credit", description = "Changes the status of a credit to ACCEPTED")
    public ResponseEntity<CreditDTO> acceptCredit(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.acceptCredit(id));
    }

    @PutMapping("/{id}/reject")
    @Operation(summary = "Reject credit", description = "Changes the status of a credit to REJECTED")
    public ResponseEntity<CreditDTO> rejectCredit(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.rejectCredit(id));
    }

    // Personal credit endpoints
    @GetMapping("/personal")
    @Operation(summary = "Get all personal credits", description = "Retrieves a list of all personal credits")
    public ResponseEntity<List<PersonalCreditDTO>> getAllPersonalCredits() {
        return ResponseEntity.ok(creditService.getAllPersonalCredits());
    }

    @GetMapping("/personal/{id}")
    @Operation(summary = "Get a personal credit by ID", description = "Retrieves a personal credit by its ID")
    public ResponseEntity<PersonalCreditDTO> getPersonalCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getPersonalCreditById(id));
    }

    @GetMapping("/personal/purpose/{purpose}")
    @Operation(summary = "Get personal credits by purpose", description = "Retrieves personal credits with a specific purpose")
    public ResponseEntity<List<PersonalCreditDTO>> getPersonalCreditsByPurpose(@PathVariable String purpose) {
        return ResponseEntity.ok(creditService.getPersonalCreditsByPurpose(purpose));
    }

    @GetMapping("/personal/client/{clientId}")
    @Operation(summary = "Get personal credits by client ID", description = "Retrieves personal credits for a specific client")
    public ResponseEntity<List<PersonalCreditDTO>> getPersonalCreditsByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.getPersonalCreditsByClientId(clientId));
    }

    @PostMapping("/personal")
    @Operation(summary = "Create a new personal credit", description = "Creates a new personal credit with the provided details")
    public ResponseEntity<PersonalCreditDTO> createPersonalCredit(@RequestBody PersonalCreditDTO personalCreditDTO) {
        return new ResponseEntity<>(creditService.savePersonalCredit(personalCreditDTO), HttpStatus.CREATED);
    }

    @PutMapping("/personal/{id}")
    @Operation(summary = "Update a personal credit", description = "Updates an existing personal credit with the provided details")
    public ResponseEntity<PersonalCreditDTO> updatePersonalCredit(@PathVariable Long id, @RequestBody PersonalCreditDTO personalCreditDTO) {
        return ResponseEntity.ok(creditService.updatePersonalCredit(id, personalCreditDTO));
    }

    // Real estate credit endpoints
    @GetMapping("/real-estate")
    @Operation(summary = "Get all real estate credits", description = "Retrieves a list of all real estate credits")
    public ResponseEntity<List<RealEstateCreditDTO>> getAllRealEstateCredits() {
        return ResponseEntity.ok(creditService.getAllRealEstateCredits());
    }

    @GetMapping("/real-estate/{id}")
    @Operation(summary = "Get a real estate credit by ID", description = "Retrieves a real estate credit by its ID")
    public ResponseEntity<RealEstateCreditDTO> getRealEstateCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getRealEstateCreditById(id));
    }

    @GetMapping("/real-estate/property-type/{propertyType}")
    @Operation(summary = "Get real estate credits by property type", description = "Retrieves real estate credits with a specific property type")
    public ResponseEntity<List<RealEstateCreditDTO>> getRealEstateCreditsByPropertyType(@PathVariable String propertyType) {
        return ResponseEntity.ok(creditService.getRealEstateCreditsByPropertyType(propertyType));
    }

    @GetMapping("/real-estate/client/{clientId}")
    @Operation(summary = "Get real estate credits by client ID", description = "Retrieves real estate credits for a specific client")
    public ResponseEntity<List<RealEstateCreditDTO>> getRealEstateCreditsByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.getRealEstateCreditsByClientId(clientId));
    }

    @PostMapping("/real-estate")
    @Operation(summary = "Create a new real estate credit", description = "Creates a new real estate credit with the provided details")
    public ResponseEntity<RealEstateCreditDTO> createRealEstateCredit(@RequestBody RealEstateCreditDTO realEstateCreditDTO) {
        return new ResponseEntity<>(creditService.saveRealEstateCredit(realEstateCreditDTO), HttpStatus.CREATED);
    }

    @PutMapping("/real-estate/{id}")
    @Operation(summary = "Update a real estate credit", description = "Updates an existing real estate credit with the provided details")
    public ResponseEntity<RealEstateCreditDTO> updateRealEstateCredit(@PathVariable Long id, @RequestBody RealEstateCreditDTO realEstateCreditDTO) {
        return ResponseEntity.ok(creditService.updateRealEstateCredit(id, realEstateCreditDTO));
    }

    // Professional credit endpoints
    @GetMapping("/professional")
    @Operation(summary = "Get all professional credits", description = "Retrieves a list of all professional credits")
    public ResponseEntity<List<ProfessionalCreditDTO>> getAllProfessionalCredits() {
        return ResponseEntity.ok(creditService.getAllProfessionalCredits());
    }

    @GetMapping("/professional/{id}")
    @Operation(summary = "Get a professional credit by ID", description = "Retrieves a professional credit by its ID")
    public ResponseEntity<ProfessionalCreditDTO> getProfessionalCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getProfessionalCreditById(id));
    }

    @GetMapping("/professional/company/{companyName}")
    @Operation(summary = "Get professional credits by company name", description = "Retrieves professional credits for a specific company")
    public ResponseEntity<List<ProfessionalCreditDTO>> getProfessionalCreditsByCompanyName(@PathVariable String companyName) {
        return ResponseEntity.ok(creditService.getProfessionalCreditsByCompanyName(companyName));
    }

    @GetMapping("/professional/purpose/{purpose}")
    @Operation(summary = "Get professional credits by purpose", description = "Retrieves professional credits with a specific purpose")
    public ResponseEntity<List<ProfessionalCreditDTO>> getProfessionalCreditsByPurpose(@PathVariable String purpose) {
        return ResponseEntity.ok(creditService.getProfessionalCreditsByPurpose(purpose));
    }

    @GetMapping("/professional/client/{clientId}")
    @Operation(summary = "Get professional credits by client ID", description = "Retrieves professional credits for a specific client")
    public ResponseEntity<List<ProfessionalCreditDTO>> getProfessionalCreditsByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.getProfessionalCreditsByClientId(clientId));
    }

    @PostMapping("/professional")
    @Operation(summary = "Create a new professional credit", description = "Creates a new professional credit with the provided details")
    public ResponseEntity<ProfessionalCreditDTO> createProfessionalCredit(@RequestBody ProfessionalCreditDTO professionalCreditDTO) {
        return new ResponseEntity<>(creditService.saveProfessionalCredit(professionalCreditDTO), HttpStatus.CREATED);
    }

    @PutMapping("/professional/{id}")
    @Operation(summary = "Update a professional credit", description = "Updates an existing professional credit with the provided details")
    public ResponseEntity<ProfessionalCreditDTO> updateProfessionalCredit(@PathVariable Long id, @RequestBody ProfessionalCreditDTO professionalCreditDTO) {
        return ResponseEntity.ok(creditService.updateProfessionalCredit(id, professionalCreditDTO));
    }
}
