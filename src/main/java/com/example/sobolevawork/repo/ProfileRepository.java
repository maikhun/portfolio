package com.example.sobolevawork.repo;

import com.example.sobolevawork.model.Profile;
import com.example.sobolevawork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByUser(User user);

}
