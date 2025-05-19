package com.example.backend.dtos;

import com.example.backend.enums.CreditStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreditDTO {
    private Long id;
    private Date requestDate;
    private CreditStatus status;
    private Date acceptanceDate;
    private Double amount;
    private Integer duration;
    private Double interestRate;
    private Long clientId;
    private String creditType;
}
