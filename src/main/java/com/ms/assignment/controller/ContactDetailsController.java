package com.ms.assignment.controller;

import com.ms.assignment.entity.ContactDetails;
import com.ms.assignment.req.ContactDetailsReq;
import com.ms.assignment.service.ContactDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/contact-details")
public class ContactDetailsController {

    private final ContactDetailsService service;

    @GetMapping
    public ResponseEntity<Page<ContactDetails>> findAll(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageNumber, name));
    }

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody ContactDetailsReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ContactDetailsReq req) {
        service.update(req, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
