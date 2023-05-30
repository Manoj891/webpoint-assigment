package com.ms.assignment.service.impl;

import com.ms.assignment.entity.ContactDetails;
import com.ms.assignment.exception.CustomException;
import com.ms.assignment.repository.ContactDetailsRepository;
import com.ms.assignment.req.ContactDetailsReq;
import com.ms.assignment.service.ContactDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactDetailsServiceImpl implements ContactDetailsService {
    private final ContactDetailsRepository repository;


    @Override
    public Page<ContactDetails> findAll(int pageNumber, String name) {
        final Pageable pageable = PageRequest.of(pageNumber, 100);
        return name.length() > 0 ?
                repository.findByFullName(name, pageable) :
                repository.findAll(pageable);
    }

    @Override
    public Long save(ContactDetailsReq req) {
        return repository.save(ContactDetails.builder()
                .emailAddress(req.getEmailAddress())
                .fullName(req.getFullName())
                .mobileNumber(req.getCountryCode() + "-" + req.getMobileNumber())
                .build()).getId();
    }

    @Override
    public void update(ContactDetailsReq req, Long id) {
        ContactDetails obj = repository.findById(id).orElseThrow(() -> new CustomException("Invalid id "));
        obj.setFullName(req.getFullName());
        obj.setEmailAddress(req.getEmailAddress());
        obj.setMobileNumber(req.getCountryCode() + "-" + req.getMobileNumber());
        repository.save(obj);
    }

    @Override
    public void delete(Long id) {
        ContactDetails obj = repository.findById(id).orElseThrow(() -> new CustomException("Invalid id "));
        repository.delete(obj);
    }
}
