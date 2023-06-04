package com.example.sobolevawork.model;

import jakarta.persistence.*;
import lombok.Data;



/**
 *
 * Сущность "Аватар пользователя"
 *
 * */

@Entity
@Table(name = "user_avatar")
@Data
public class ProfileAvatar {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "originalFileName")
    private String originalFileName;

    @Column(name = "size")
    private Long size;

    @Column(name = "contentType")
    private String contentType;

    @Lob
    private byte[] bytes;

    @OneToOne
    private Profile profile;
}
