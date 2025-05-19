package com.example.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.example.backend.enums.PropertyType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RealEstateCreditDTO extends CreditDTO {
    private PropertyType propertyType;
}
