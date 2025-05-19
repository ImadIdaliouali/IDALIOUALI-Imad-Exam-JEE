package com.example.backend.entities;

import com.example.backend.enums.CreditStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CREDIT_TYPE")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    
    @Enumerated(EnumType.STRING)
    private CreditStatus status;
    
    @Temporal(TemporalType.DATE)
    private Date acceptanceDate;
    
    private Double amount;
    private Integer duration; // in months
    private Double interestRate;
    
    @ManyToOne
    private Client client;
    
    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Repayment> repayments = new ArrayList<>();
}
