package com.ms.assignment.service;

import com.ms.assignment.entity.ContactDetails;
import com.ms.assignment.repository.ContactDetailsRepository;
import com.ms.assignment.req.ContactDetailsReq;
import com.ms.assignment.service.impl.ContactDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContactDetailsServiceTest {

    @Mock
    private ContactDetailsRepository repository;

    @InjectMocks
    private ContactDetailsServiceImpl service;

    public ContactDetailsServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll_WithEmptyName_ReturnsContactDetailsList() {
        // Arrange
        String name = "";
        List<ContactDetails> expectedList = new ArrayList<>();
        when(repository.findByFullName(name)).thenReturn(expectedList);

        // Act
        List<ContactDetails> result = service.findAll(name);

        // Assert
        assertEquals(expectedList, result);
        verify(repository, times(1)).findByFullName(name);
        verify(repository, never()).findAll();
    }

    @Test
    void findAll_WithNonEmptyName_ReturnsAllContactDetailsList() {
        // Arrange
        String name = "Manoj";
        List<ContactDetails> expectedList = new ArrayList<>();
        when(repository.findAll()).thenReturn(expectedList);

        // Act
        List<ContactDetails> result = service.findAll(name);

        // Assert
        assertEquals(expectedList, result);
        verify(repository, never()).findByFullName(name);
        verify(repository, times(1)).findAll();
    }

    @Test
    void save_ValidRequest_ReturnsContactDetailsId() {
        // Arrange
        ContactDetailsReq req = new ContactDetailsReq();
        req.setEmailAddress("mkm@mymail.com");
        req.setFullName("Manoj Kumar");
        req.setCountryCode("+977");
        req.setMobileNumber("9876543210");

        ContactDetails savedContactDetails = new ContactDetails();
        savedContactDetails.setId(1L);
        when(repository.save(any(ContactDetails.class))).thenReturn(savedContactDetails);

        // Act
        Long result = service.save(req);

        // Assert
        assertEquals(savedContactDetails.getId(), result);
        verify(repository, times(1)).save(any(ContactDetails.class));
    }

    @Test
    void update_ValidRequest_UpdatesContactDetails() {
        // Arrange
        Long id = 1L;
        ContactDetailsReq req = new ContactDetailsReq();
        req.setEmailAddress("mkm@mymail.com");
        req.setFullName("Manoj Kumar");
        req.setCountryCode("+977");
        req.setMobileNumber("9876543210");

        ContactDetails existingContactDetails = new ContactDetails();
        existingContactDetails.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(existingContactDetails));

        // Act
        service.update(req, id);

        // Assert
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(existingContactDetails);
        assertEquals(req.getFullName(), existingContactDetails.getFullName());
        assertEquals(req.getEmailAddress(), existingContactDetails.getEmailAddress());
        assertEquals(req.getCountryCode() + "-" + req.getMobileNumber(), existingContactDetails.getMobileNumber());
    }

    @Test
    void delete_ValidId_DeletesContactDetails() {
        // Arrange
        Long id = 1L;
        ContactDetails existingContactDetails = new ContactDetails();
        existingContactDetails.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(existingContactDetails));

        // Act
        service.delete(id);

        // Assert
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(existingContactDetails);
    }
}
