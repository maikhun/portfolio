package com.example.sobolevawork.model;

import jakarta.persistence.*;
import lombok.Data;



/**
 *
 * Сущность "Аватар профиля"
 *
 * */
@Entity
@Table(name = "user_avatar")
@Data
public class ProfileAvatar {
    // Id - идентификатор аватара
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    // Name - наименование изображения
    @Column(name = "name")
    private String name;

    // OriginalFileName - оригинальное название изображения
    @Column(name = "originalFileName")
    private String originalFileName;

    // Size - размер загружаемого изображения
    @Column(name = "size")
    private Long size;

    // ContentType - расширение изображения
    @Column(name = "contentType")
    private String contentType;

    // Bytes - Байт-код изображения
    @Lob
    private byte[] bytes;

    // Связь "Профиль и Аватар"
    @OneToOne
    private Profile profile;
}
