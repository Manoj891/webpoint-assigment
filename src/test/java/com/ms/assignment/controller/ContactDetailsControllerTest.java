package com.ms.assignment.controller;

import com.ms.assignment.entity.ContactDetails;
import com.ms.assignment.req.ContactDetailsReq;
import com.ms.assignment.service.ContactDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContactDetailsControllerTest {

    private final ContactDetailsService service = mock(ContactDetailsService.class);
    private final ContactDetailsController controller = new ContactDetailsController(service);

    @Test
    void findAll_WithEmptyName_ReturnsOk() {
        // Arrange
        String name = "";
        List<ContactDetails> expectedList = Collections.emptyList();
        when(service.findAll(name)).thenReturn(expectedList);

        // Act
        ResponseEntity<List<ContactDetails>> response = controller.findAll(name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedList, response.getBody());
        verify(service, times(1)).findAll(name);
    }

    @Test
    void save_ValidRequest_ReturnsCreated() {
        // Arrange
        ContactDetailsReq req = new ContactDetailsReq();
        Long expectedId = 1L;
        when(service.save(req)).thenReturn(expectedId);

        // Act
        ResponseEntity<Long> response = controller.save(req);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedId, response.getBody());
        verify(service, times(1)).save(req);
    }

    @Test
    void update_ValidRequest_ReturnsNoContent() {
        // Arrange
        Long id = 1L;
        ContactDetailsReq req = new ContactDetailsReq();

        // Act
        ResponseEntity<Void> response = controller.update(id, req);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).update(req, id);
    }

    @Test
    void delete_ValidId_ReturnsNoContent() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Void> response = controller.delete(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).delete(id);
    }
}
