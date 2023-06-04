package com.example.sobolevawork.service;

import com.example.sobolevawork.model.Profile;
import com.example.sobolevawork.model.ProfileAvatar;
import com.example.sobolevawork.repo.ProfileAvatarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AvatarService {

    private final ProfileAvatarRepository profileAvatarRepository;

    public boolean createAvatar(MultipartFile file, Profile profile) throws IOException {
        ProfileAvatar avatar;

        if (file.getSize() == 0) {
            return false;
        }
        avatar = toProfileAvatar(file);
        avatar.setProfile(profile);
        avatar.setId(profile.getId());
        profileAvatarRepository.save(avatar);
        return true;
    }

    private ProfileAvatar toProfileAvatar(MultipartFile file) throws IOException {
        ProfileAvatar avatar = new ProfileAvatar();
        avatar.setName(file.getName());
        avatar.setOriginalFileName(file.getOriginalFilename());
        avatar.setContentType(file.getContentType());
        avatar.setSize(file.getSize());
        avatar.setBytes(file.getBytes());
        return avatar;
    }

}
