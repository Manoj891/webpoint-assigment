package com.ms.assignment.service;

import com.ms.assignment.entity.ContactDetails;
import com.ms.assignment.repository.ContactDetailsRepository;
import com.ms.assignment.req.ContactDetailsReq;
import com.ms.assignment.service.impl.ContactDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContactDetailsServiceTest {

    @Mock
    private ContactDetailsRepository repository;

    @InjectMocks
    private ContactDetailsServiceImpl service;
    private final ContactDetailsReq req = ContactDetailsReq.builder().emailAddress("mkm@mymail.com").fullName("Manoj Kumar").countryCode("+977").mobileNumber("9876543210").build();

    public ContactDetailsServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void get_ValidRequest_ReturnsContactDetailsId() {
        ContactDetails savedContactDetails = ContactDetails.builder().id(1L).emailAddress("mkm@mymail.com").fullName("Manoj Kumar").mobileNumber("+977-9876543210").build();
        when(repository.save(any(ContactDetails.class))).thenReturn(savedContactDetails);

        Long result = service.save(req);

        assertEquals(savedContactDetails.getId(), result);
        verify(repository, times(1)).save(any(ContactDetails.class));
    }

    @Test
    void save_ValidRequest_ReturnsContactDetailsId() {
        ContactDetails savedContactDetails = ContactDetails.builder().id(1L).emailAddress("mkm@mymail.com").fullName("Manoj Kumar").mobileNumber("+977-9876543210").build();

        when(repository.save(any(ContactDetails.class))).thenReturn(savedContactDetails);

        Long result = service.save(req);

        assertEquals(savedContactDetails.getId(), result);
        verify(repository, times(1)).save(any(ContactDetails.class));
    }

    @Test
    void update_ValidRequest_UpdatesContactDetails() {
        Long id = 1L;

        ContactDetails existingContactDetails = new ContactDetails();
        existingContactDetails.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(existingContactDetails));

        service.update(req, id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(existingContactDetails);
        assertEquals(req.getFullName(), existingContactDetails.getFullName());
        assertEquals(req.getEmailAddress(), existingContactDetails.getEmailAddress());
        assertEquals(req.getCountryCode() + "-" + req.getMobileNumber(), existingContactDetails.getMobileNumber());
    }

    @Test
    void delete_ValidId_DeletesContactDetails() {

        Long id = 1L;
        ContactDetails existingContactDetails = new ContactDetails();
        existingContactDetails.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(existingContactDetails));

        service.delete(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(existingContactDetails);
    }
}
