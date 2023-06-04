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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column( name = "fileName", nullable = false)
    private String fileName;

    @Column( name = "documentType", nullable = false)
    private String documentType;

    @Column( name = "year", nullable = true)
    private String year;

    @Column( name = "skillType", nullable = false)
    private String skillType;

    @Column( name = "haveDoc", nullable = true)
    private Boolean haveDoc;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "profileId")
    private Profile profile;
}
