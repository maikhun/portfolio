package com.example.sobolevawork.model;

import com.example.sobolevawork.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * Сущность "Пользователь"
 *
 * */
@Entity
@Table(name = "user_info")
@Data
public class User implements UserDetails {
    // Id - идентификатор пользователя
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // FirstName - имя пользователя
    @Column(name = "firstname", nullable = false)
    private String firstname;

    // SecondName - фамилия пользователя
    @Column(name = "secondname", nullable = false)
    private String secondname;

    // Email - почта пользователя (логин)
    @Column(name = "email", unique = true)
    private String email;

    // Gender - пол пользователя
    @Column(name="gender", nullable = false)
    private String gender;

    // Nationality - национальность пользователя
    @Column(name = "nationality", nullable = true)
    private String nationality;

    // Education - образование пользователя
    @Column(name="education", nullable = false)
    private String education;

    // Password - пароль пользователя
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "active")
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
