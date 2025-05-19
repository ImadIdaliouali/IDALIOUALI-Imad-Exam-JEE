package com.example.backend.entities;

import com.example.backend.enums.PropertyType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("REAL_ESTATE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RealEstateCredit extends Credit {
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
}
