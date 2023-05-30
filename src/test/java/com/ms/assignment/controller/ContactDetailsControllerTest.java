package com.ms.assignment.controller;

import com.ms.assignment.req.ContactDetailsReq;
import com.ms.assignment.service.ContactDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RunWith(MockitoJUnitRunner.class)
class ContactDetailsControllerTest {

    @Mock
    private ContactDetailsService service;

    @InjectMocks
    private ContactDetailsController controller;
    private final ContactDetailsReq req = ContactDetailsReq.builder().fullName("Manoj Kumar").emailAddress("mkm@mymail.com").mobileNumber("9876543210").countryCode("+977").build();

    public ContactDetailsControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {

        Long savedId = 1L;

        Mockito.when(service.save(req)).thenReturn(savedId);
        ResponseEntity<Long> response = controller.save(req);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(savedId, response.getBody());

        Mockito.verify(service, Mockito.times(1)).save(req);
        Mockito.verifyNoMoreInteractions(service);
    }

    @Test
    void testUpdate() {
        Long id = 1L;

        ResponseEntity<Void> response = controller.update(id, req);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        Mockito.verify(service, Mockito.times(1)).update(req, id);
        Mockito.verifyNoMoreInteractions(service);
    }

    @Test
    void testDelete() {
        Long id = 1L;

        ResponseEntity<Void> response = controller.delete(id);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        Mockito.verify(service, Mockito.times(1)).delete(id);
        Mockito.verifyNoMoreInteractions(service);
    }
}



