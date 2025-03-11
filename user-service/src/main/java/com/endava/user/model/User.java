package com.endava.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username field must not be blank")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password field must not be blank")
    private String password;

    @NotBlank(message = "Email field must not be blank")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Phone number field must not be blank")
    @Column(unique = true)
    private String phoneNumber;

    private String address;
    private Boolean isDeleted = false;

    public User() {}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='********'" +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
