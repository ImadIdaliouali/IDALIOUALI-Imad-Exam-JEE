package com.example.backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("PERSONAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PersonalCredit extends Credit {
    private String purpose; // car purchase, education, renovations, etc.
}
