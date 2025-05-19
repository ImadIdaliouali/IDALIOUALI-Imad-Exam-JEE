package com.example.backend.repositories;

import com.example.backend.entities.RealEstateCredit;
import com.example.backend.enums.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealEstateCreditRepository extends JpaRepository<RealEstateCredit, Long> {
    List<RealEstateCredit> findByPropertyType(PropertyType propertyType);
    List<RealEstateCredit> findByClientId(Long clientId);
}
