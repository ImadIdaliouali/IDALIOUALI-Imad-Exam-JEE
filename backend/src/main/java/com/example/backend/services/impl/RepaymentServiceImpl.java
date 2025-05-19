package com.example.backend.services.impl;

import lombok.RequiredArgsConstructor;
import com.example.backend.dtos.RepaymentDTO;
import com.example.backend.entities.Repayment;
import com.example.backend.enums.RepaymentType;
import com.example.backend.mappers.RepaymentMapper;
import com.example.backend.repositories.RepaymentRepository;
import com.example.backend.services.RepaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RepaymentServiceImpl implements RepaymentService {
    private final RepaymentRepository repaymentRepository;
    private final RepaymentMapper repaymentMapper;

    @Override
    public List<RepaymentDTO> getAllRepayments() {
        return repaymentMapper.toDTOList(repaymentRepository.findAll());
    }

    @Override
    public RepaymentDTO getRepaymentById(Long id) {
        return repaymentRepository.findById(id)
                .map(repaymentMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Repayment not found with id: " + id));
    }

    @Override
    public List<RepaymentDTO> getRepaymentsByCreditId(Long creditId) {
        return repaymentMapper.toDTOList(repaymentRepository.findByCreditId(creditId));
    }

    @Override
    public List<RepaymentDTO> getRepaymentsByType(RepaymentType type) {
        return repaymentMapper.toDTOList(repaymentRepository.findByType(type));
    }

    @Override
    public List<RepaymentDTO> getRepaymentsByDateRange(Date startDate, Date endDate) {
        return repaymentMapper.toDTOList(repaymentRepository.findByDateBetween(startDate, endDate));
    }

    @Override
    public RepaymentDTO saveRepayment(RepaymentDTO repaymentDTO) {
        Repayment repayment = repaymentMapper.toEntity(repaymentDTO);
        if (repayment.getDate() == null) {
            repayment.setDate(new Date());
        }
        return repaymentMapper.toDTO(repaymentRepository.save(repayment));
    }

    @Override
    public RepaymentDTO updateRepayment(Long id, RepaymentDTO repaymentDTO) {
        return repaymentRepository.findById(id)
                .map(existingRepayment -> {
                    repaymentDTO.setId(id);
                    return repaymentMapper.toDTO(repaymentRepository.save(repaymentMapper.toEntity(repaymentDTO)));
                })
                .orElseThrow(() -> new RuntimeException("Repayment not found with id: " + id));
    }

    @Override
    public void deleteRepayment(Long id) {
        repaymentRepository.deleteById(id);
    }
}
