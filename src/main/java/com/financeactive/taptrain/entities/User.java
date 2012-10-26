package com.financeactive.taptrain.entities;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author olemerdy
 * @since 24/10/12
 */
@Entity
public class User {

    static final String PATTERN_EMAIL = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+((\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)?)+@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9\\-]*[a-z0-9])?$";

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    private Honorific honorific;

    @NotNull
    @Size(min = 3)
    private String firstName;

    @NotNull
    @Size(min = 2)
    private String lastName;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(unique = true)
    @Email
    @NotNull
    @Pattern(regexp = PATTERN_EMAIL)
    private String email;

    public Long getId() {
        return id;
    }

    @NonVisual
    public boolean isNew() {
        return id == null;
    }

    public Honorific getHonorific() {
        return honorific;
    }

    public void setHonorific(Honorific honorific) {
        this.honorific = honorific;
    }

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", honorific=" + honorific +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                '}';
    }

    public interface Properties {
        String email = "email";

        String id = "id";
    }
}
