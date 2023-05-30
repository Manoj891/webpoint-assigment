package com.ms.assignment.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(indexes = {
        @Index(name = "index_contact_details_full_name", columnList = "full_name"),
        @Index(name = "index_contact_details_mobile_number", columnList = "mobile_number", unique = true)})
public class ContactDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", length = 60)
    private String fullName;
    @Column(name = "email_address", length = 150)
    private String emailAddress;
    @Column(name = "mobile_number", length = 19)
    private String mobileNumber;

}
