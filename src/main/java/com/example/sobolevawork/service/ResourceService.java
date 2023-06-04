package com.example.sobolevawork.service;

import com.example.sobolevawork.model.DocumentEntity;
import com.example.sobolevawork.model.DocumentResource;
import com.example.sobolevawork.repo.DocumentRepository;
import com.example.sobolevawork.repo.DocumentResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResourceService {

    private final DocumentRepository documentRepository;
    private final DocumentResourceRepository documentResourceRepository;

    public boolean createDocumentResource(MultipartFile file, DocumentEntity document) throws IOException {
        DocumentResource resource;

        if (file.getSize() == 0) {
            return false;
        }
        resource = toDocumentResource(file);
        resource.setDocument(document);
        resource.setId(document.getId());
        document.setHaveDoc(true);
        documentRepository.save(document);
        documentResourceRepository.save(resource);
        return true;
    }

    private DocumentResource toDocumentResource(MultipartFile file) throws IOException {
        DocumentResource resource = new DocumentResource();
        resource.setOriginalFileName(file.getOriginalFilename());
        resource.setContentType(file.getContentType());
        resource.setSize(file.getSize());
        resource.setBytes(file.getBytes());
        resource.setName(file.getName());
        return resource;
    }

    public Optional<DocumentResource> findById(Long id) {
        return documentResourceRepository.findById(id);
    }

}
