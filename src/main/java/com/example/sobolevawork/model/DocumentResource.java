package com.example.sobolevawork.model;

import jakarta.persistence.*;
import lombok.Data;


/**
 *
 * Сущность "Файл документа"
 *
 * */
@Entity
@Table(name = "document_resource")
@Data
public class DocumentResource {
    // Id - идентификатор файла
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    // Name - наименование файла
    @Column(name = "name")
    private String name;

    // OriginalFileName - оригинальное название загружаемого файла
    @Column(name = "originalFileName")
    private String originalFileName;

    // Size - размер загружаемого файла
    @Column(name = "size")
    private Long size;

    // ContentType - тип загружаемого файла
    @Column(name = "contentType")
    private String contentType;

    // Bytes - Байт-код файла
    @Lob
    private byte[] bytes;

    // Связь "Документ и Файл"
    @OneToOne
    private DocumentEntity document;

}
