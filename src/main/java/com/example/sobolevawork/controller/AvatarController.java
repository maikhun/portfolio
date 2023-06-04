package com.example.sobolevawork.controller;

import com.example.sobolevawork.model.ProfileAvatar;
import com.example.sobolevawork.repo.DocumentResourceRepository;
import com.example.sobolevawork.repo.ProfileAvatarRepository;
import com.example.sobolevawork.service.ProfileService;
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
public class AvatarController {
    private final ProfileAvatarRepository profileAvatarRepository;

    @GetMapping("/avatars/{id}")
    private ResponseEntity<?> getAvatarId(@PathVariable Long id) {
        ProfileAvatar avatar = profileAvatarRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", avatar.getOriginalFileName())
                .contentType(MediaType.valueOf(avatar.getContentType()))
                .contentLength(avatar.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(avatar.getBytes())));
    }
}
