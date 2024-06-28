package com.hendisantika.springboot.swagger.model;

import lombok.*;
import org.jfairy.producer.person.Address;
import org.jfairy.producer.person.Person;
import org.joda.time.DateTime;

/**
 * Created by hendisantika on 4/24/17.
 */

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer employeeId;
    private Address address;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Person.Sex sex;
    private String telephoneNumber;
    private DateTime dateOfBirth;
    private Integer age;
    private String companyEmail;
    private String nationalIdentityCardNumber;
    private String nationalIdentificationNumber;

    public Employee(int employeeId, Person p) {
        this.employeeId = employeeId;
        this.nationalIdentityCardNumber = p.nationalIdentificationNumber();
        this.address = p.getAddress();
        this.firstName = p.firstName();
        this.middleName = p.middleName();
        this.lastName = p.lastName();
        this.email = p.email();
        this.sex = p.sex();
        this.telephoneNumber = p.telephoneNumber();
        this.dateOfBirth = p.dateOfBirth();
        this.age = p.age();
        this.nationalIdentificationNumber = p.nationalIdentificationNumber();
        this.companyEmail = p.companyEmail();
    }

}
