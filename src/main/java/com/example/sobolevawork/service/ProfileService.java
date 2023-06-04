package com.example.sobolevawork.service;

import com.example.sobolevawork.model.Profile;
import com.example.sobolevawork.model.User;
import com.example.sobolevawork.repo.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    // Создание профиля пользователя
    public boolean createProfile(User user) {

        // Проверка - есть ли создающийся профиль уже в БД?
        if (profileRepository.findById(user.getId()) == null)
            return false;

        // Создание профиля
        Profile profile = new Profile();
        profile.setId(user.getId());
        profile.setUser(user);
        profileRepository.save(profile);

        return true;
    }

    public Profile getProfileByUser(User user) {
        return profileRepository.findByUser(user);
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    // Создание ссылки на профиль


//    public String createLink(Principal principal) {
//        var user = userRepository.findByEmail(principal.getName());
//        String baseURL = "localhost:8080/user/";
//        return baseURL + user.getId();
//    }
//
//    public boolean copyToClipboard(Principal principal) {
//        String url = createLink(principal);
//        StringSelection selection = new StringSelection(url);
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        clipboard.setContents(selection, selection);
//        return true;
//    }


}
