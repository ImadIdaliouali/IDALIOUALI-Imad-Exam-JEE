package com.example.backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("PROFESSIONAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProfessionalCredit extends Credit {
    private String purpose;
    private String companyName;
}
