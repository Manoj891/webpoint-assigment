package com.ms.assignment.repository;

import com.ms.assignment.entity.ContactDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {
    Page<ContactDetails> findByFullName(String name, Pageable pageable);


}
