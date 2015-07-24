/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vizaco.onlinecontrol.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vizaco.onlinecontrol.enumeration.Gender;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
public abstract class Person extends BaseEntity implements Comparable<Person> {

    @NotEmpty
    @Column(name = "first_name")
    protected String firstName;

    @NotEmpty
    @Column(name = "last_name")
    protected String lastName;

    @NotEmpty
    @Column(name = "middle_name")
    protected String middleName;

    @NotNull
    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    protected Gender gender;

    @NotNull
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    protected Date dateOfBirth;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    protected User user;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (middleName != null ? !middleName.equals(person.middleName) : person.middleName != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(person.dateOfBirth) : person.dateOfBirth != null) return false;
        return gender == person.gender;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Person person) {
        int result = 0;

        if (this == person) return 0;
        if (person == null) return 1;

        if (lastName != null && person.lastName != null) {
            result = lastName.compareTo(person.lastName);
        }else if(lastName != null && person.lastName == null){
            result = 1;
        }else if(lastName == null && person.lastName != null){
            result = -1;
        }
        if (result != 0) return result;

        if (firstName != null && person.firstName != null) {
            result = firstName.compareTo(person.firstName);
        }else if(firstName != null && person.firstName == null){
            result = 1;
        }else if(firstName == null && person.firstName != null){
            result = -1;
        }
        if (result != 0) return result;

        if (middleName != null && person.middleName != null) {
            result = middleName.compareTo(person.middleName);
        }else if(middleName != null && person.middleName == null){
            result = 1;
        }else if(middleName == null && person.middleName != null){
            result = -1;
        }
        if (result != 0) return result;

        if (dateOfBirth != null && person.dateOfBirth != null) {
            result = dateOfBirth.compareTo(person.dateOfBirth);
        }else if(dateOfBirth != null && person.dateOfBirth == null){
            result = 1;
        }else if(dateOfBirth == null && person.dateOfBirth != null){
            result = -1;
        }

        return result;

    }
}
