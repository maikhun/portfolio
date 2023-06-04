package com.example.sobolevawork.model;

import jakarta.persistence.*;
import lombok.Data;


/**
 *
 * Сущность "Данные документа"
 *
 * */

@Entity
@Table(name = "document_resource")
@Data
public class DocumentResource {

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
    private DocumentEntity document;

}
