package com.example.sobolevawork.repo;

import com.example.sobolevawork.model.DocumentEntity;
import com.example.sobolevawork.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    List<DocumentEntity> findAllByProfile(Profile profile);
}
