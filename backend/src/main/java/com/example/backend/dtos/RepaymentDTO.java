package com.example.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.backend.enums.RepaymentType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepaymentDTO {
    private Long id;
    private Date date;
    private Double amount;
    private RepaymentType type;
    private Long creditId;
}
