package com.example.sobolevawork.repo;

import com.example.sobolevawork.model.Profile;
import com.example.sobolevawork.model.ProfileAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileAvatarRepository extends JpaRepository<ProfileAvatar, Long> {
    ProfileAvatar findByProfile(Profile profile);
}
