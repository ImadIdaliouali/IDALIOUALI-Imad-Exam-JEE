package com.example.backend.config;

import com.example.backend.entities.*;
import com.example.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import com.example.backend.enums.*;
import com.example.backend.security.entities.AppRole;
import com.example.backend.security.entities.AppUser;
import com.example.backend.security.repositories.AppRoleRepository;
import com.example.backend.security.repositories.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final PersonalCreditRepository personalCreditRepository;
    private final RealEstateCreditRepository realEstateCreditRepository;
    private final ProfessionalCreditRepository professionalCreditRepository;
    private final RepaymentRepository repaymentRepository;
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Initialize roles
        if (roleRepository.count() == 0) {
            Arrays.asList("ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_CLIENT")
                    .forEach(roleName -> roleRepository.save(AppRole.builder().roleName(roleName).build()));
        }

        // Initialize users
        if (userRepository.count() == 0) {
            AppRole adminRole = roleRepository.findByRoleName("ROLE_ADMIN").orElseThrow();
            AppRole employeeRole = roleRepository.findByRoleName("ROLE_EMPLOYEE").orElseThrow();
            AppRole clientRole = roleRepository.findByRoleName("ROLE_CLIENT").orElseThrow();

            // Admin user
            AppUser admin = AppUser.builder()
                    .username("admin@bank.com")
                    .password(passwordEncoder.encode("admin123"))
                    .fullName("Admin User")
                    .roles(List.of(adminRole))
                    .build();
            userRepository.save(admin);

            // Employee user
            AppUser employee = AppUser.builder()
                    .username("employee@bank.com")
                    .password(passwordEncoder.encode("employee123"))
                    .fullName("Employee User")
                    .roles(List.of(employeeRole))
                    .build();
            userRepository.save(employee);

            // Client user
            AppUser client = AppUser.builder()
                    .username("client@example.com")
                    .password(passwordEncoder.encode("client123"))
                    .fullName("Client User")
                    .roles(List.of(clientRole))
                    .build();
            userRepository.save(client);
        }

        // Initialize clients
        if (clientRepository.count() == 0) {
            Client client1 = Client.builder()
                    .name("John Doe")
                    .email("john.doe@example.com")
                    .build();
            clientRepository.save(client1);

            Client client2 = Client.builder()
                    .name("Jane Smith")
                    .email("jane.smith@example.com")
                    .build();
            clientRepository.save(client2);

            // Initialize personal credits
            PersonalCredit personalCredit1 = PersonalCredit.builder()
                    .requestDate(new Date())
                    .status(CreditStatus.ACCEPTED)
                    .acceptanceDate(new Date())
                    .amount(10000.0)
                    .duration(24)
                    .interestRate(3.5)
                    .purpose("Car Purchase")
                    .client(client1)
                    .build();
            personalCreditRepository.save(personalCredit1);

            // Initialize real estate credits
            RealEstateCredit realEstateCredit1 = RealEstateCredit.builder()
                    .requestDate(new Date())
                    .status(CreditStatus.PENDING)
                    .amount(200000.0)
                    .duration(240)
                    .interestRate(2.75)
                    .propertyType(PropertyType.APARTMENT)
                    .client(client1)
                    .build();
            realEstateCreditRepository.save(realEstateCredit1);

            // Initialize professional credits
            ProfessionalCredit professionalCredit1 = ProfessionalCredit.builder()
                    .requestDate(new Date())
                    .status(CreditStatus.ACCEPTED)
                    .acceptanceDate(new Date())
                    .amount(50000.0)
                    .duration(36)
                    .interestRate(4.0)
                    .purpose("Business Expansion")
                    .companyName("Smith Consulting")
                    .client(client2)
                    .build();
            professionalCreditRepository.save(professionalCredit1);

            // Initialize repayments
            Repayment repayment1 = Repayment.builder()
                    .date(new Date())
                    .amount(428.0)
                    .type(RepaymentType.MONTHLY_PAYMENT)
                    .credit(personalCredit1)
                    .build();
            repaymentRepository.save(repayment1);

            Repayment repayment2 = Repayment.builder()
                    .date(new Date())
                    .amount(1500.0)
                    .type(RepaymentType.MONTHLY_PAYMENT)
                    .credit(professionalCredit1)
                    .build();
            repaymentRepository.save(repayment2);
        }
    }
}
