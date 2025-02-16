package com.virtusa.customerapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class FullName {
    @Column(name="First_Name", length = 50, nullable = false)
    private String firstName;
    @Column(name="Last_Name", length = 50, nullable = false)
    private String lastName;
    @Column(name="Middle_Name", length = 50, nullable = true)
    private String middleName;


}
