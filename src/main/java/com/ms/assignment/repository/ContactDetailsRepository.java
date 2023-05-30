package com.ms.assignment.repository;

import com.ms.assignment.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {
    List<ContactDetails> findByFullName(String name);
}
