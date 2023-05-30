package com.ms.assignment.service;

import com.ms.assignment.entity.ContactDetails;
import com.ms.assignment.req.ContactDetailsReq;

import java.util.List;

public interface ContactDetailsService {
    List<ContactDetails> findAll(String name);

    Long save(ContactDetailsReq req);

    void update(ContactDetailsReq req, Long id);

    void delete(Long id);
}
