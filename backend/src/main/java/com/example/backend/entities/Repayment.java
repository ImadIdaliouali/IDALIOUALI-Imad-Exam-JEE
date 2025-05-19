package com.example.backend.entities;

import com.example.backend.enums.RepaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    private Double amount;
    
    @Enumerated(EnumType.STRING)
    private RepaymentType type;
    
    @ManyToOne
    private Credit credit;
}
