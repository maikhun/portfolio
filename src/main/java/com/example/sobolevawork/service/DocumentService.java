package com.example.sobolevawork.service;

import com.example.sobolevawork.model.DocumentEntity;
import com.example.sobolevawork.model.Profile;
import com.example.sobolevawork.repo.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    // Функция по формированию списка документов по профилю
    public List<DocumentEntity> getDocumentByProfile(Profile profile) {
        List<DocumentEntity> documents = new LinkedList<>(documentRepository.findAllByProfile(profile)); // Список документов по профилю
        return documents;
    }

    public boolean createDocument(DocumentEntity documentFormData, Profile profile) {
        documentFormData.setProfile(profile);
        documentFormData.setHaveDoc(false);
        documentRepository.save(documentFormData);
        return true;
    }

    public Optional<DocumentEntity> findById(Long id) {
        return documentRepository.findById(id);
    }

}
