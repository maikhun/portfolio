package com.example.sobolevawork.repo;

import com.example.sobolevawork.model.DocumentEntity;
import com.example.sobolevawork.model.DocumentResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentResourceRepository extends JpaRepository<DocumentResource, Long> {
    DocumentResource findByDocument(DocumentEntity document);
}
