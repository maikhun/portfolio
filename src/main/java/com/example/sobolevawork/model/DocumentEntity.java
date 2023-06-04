package com.example.sobolevawork.model;

import jakarta.persistence.*;
import lombok.Data;

/**
*
 * Сущность "Документ"
*
* */
@Entity
@Table(name = "document_ifo")
@Data
public class DocumentEntity {
    // Id - идентификатор документа
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // FileName - наименование файла
    @Column( name = "fileName", nullable = false)
    private String fileName;

    // DocumentType - тип документа
    @Column( name = "documentType", nullable = false)
    private String documentType;

    // Year - год получения документа
    @Column( name = "year", nullable = true)
    private String year;

    // SkillType - тип навыка
    @Column( name = "skillType", nullable = false)
    private String skillType;

    // HaveDoc - прикреплен ли к документу файл?
    @Column( name = "haveDoc", nullable = true)
    private Boolean haveDoc;

    // Связь "Профиль и Документ"
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "profileId")
    private Profile profile;
}
