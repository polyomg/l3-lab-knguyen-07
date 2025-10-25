package com.poly.lab8.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "Username")
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Fullname", nullable = false)
    private String fullname;

    @Column(name = "Email")
    private String email;

    @Column(name = "Photo")
    private String photo;

    @Column(name = "Activated")
    private Boolean activated;

    @Column(name = "Admin")
    private Boolean admin;

    // Nếu cần check quyền admin
    public boolean isAdmin() {
        return Boolean.TRUE.equals(admin);
    }
}
