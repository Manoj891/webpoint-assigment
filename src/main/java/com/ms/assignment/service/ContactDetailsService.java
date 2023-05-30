package com.ms.assignment.service;

import com.ms.assignment.entity.ContactDetails;
import com.ms.assignment.req.ContactDetailsReq;
import org.springframework.data.domain.Page;

public interface ContactDetailsService {
    Page<ContactDetails> findAll(int pageNumber, String name);

    Long save(ContactDetailsReq req);

    void update(ContactDetailsReq req, Long id);

    void delete(Long id);
}
