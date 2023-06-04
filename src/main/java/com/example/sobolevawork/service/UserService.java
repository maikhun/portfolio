package com.example.sobolevawork.service;

import com.example.sobolevawork.model.Profile;
import com.example.sobolevawork.model.User;
import com.example.sobolevawork.model.enums.Role;
import com.example.sobolevawork.repo.ProfileRepository;
import com.example.sobolevawork.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ProfileRepository profileRepository;

    // Создание пользователя
    public boolean createUserEntity(User user) {

        String email = user.getEmail();

        // Проверка - есть ли создающийся пользователь уже в БД?
        if (userRepository.findByEmail(email) != null)
            return false;

        // Создание пользователя
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Хеширование пароля
        user.getRoles().add(Role.ROLE_USER); // Добавление роли
        userRepository.save(user);

        return true;
    }

    // Изменение данных пользователя
    public boolean updateUserData(User user /* Текущий пользователь */,
                                  User updatedUser /* Данные о пользователе с формы редактирования */ ) {

        if (!updatedUser.getEmail().isBlank())
            user.setEmail(updatedUser.getEmail());

        if (!updatedUser.getGender().isBlank())
            user.setGender(updatedUser.getGender());

        if (!updatedUser.getFirstname().isBlank())
            user.setFirstname(updatedUser.getFirstname());

        if (!updatedUser.getSecondname().isBlank())
            user.setSecondname(updatedUser.getSecondname());

        if (!updatedUser.getNationality().isBlank())
            user.setNationality(updatedUser.getNationality());

        if (!updatedUser.getEducation().isBlank())
            user.setEducation(updatedUser.getEducation());

        userRepository.save(user); // Сохранение пользователя

        return true;
    }

    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email);

    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

}
