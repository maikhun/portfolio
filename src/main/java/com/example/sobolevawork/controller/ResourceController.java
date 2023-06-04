package com.example.sobolevawork.controller;

import com.example.sobolevawork.model.DocumentResource;
import com.example.sobolevawork.model.ProfileAvatar;
import com.example.sobolevawork.repo.DocumentResourceRepository;
import com.example.sobolevawork.repo.ProfileAvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ResourceController {

    private final DocumentResourceRepository documentResourceRepository;

    @GetMapping("/resources/{id}")
    private ResponseEntity<?> getDocumentId(@PathVariable Long id) {
        DocumentResource resource = documentResourceRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", resource.getOriginalFileName())
                .contentType(MediaType.valueOf(resource.getContentType()))
                .contentLength(resource.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(resource.getBytes())));
    }
}
