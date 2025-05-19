package com.example.backend.services;

import com.example.backend.dtos.RepaymentDTO;
import com.example.backend.enums.RepaymentType;

import java.util.Date;
import java.util.List;

public interface RepaymentService {
    List<RepaymentDTO> getAllRepayments();
    RepaymentDTO getRepaymentById(Long id);
    List<RepaymentDTO> getRepaymentsByCreditId(Long creditId);
    List<RepaymentDTO> getRepaymentsByType(RepaymentType type);
    List<RepaymentDTO> getRepaymentsByDateRange(Date startDate, Date endDate);
    RepaymentDTO saveRepayment(RepaymentDTO repaymentDTO);
    RepaymentDTO updateRepayment(Long id, RepaymentDTO repaymentDTO);
    void deleteRepayment(Long id);
}
