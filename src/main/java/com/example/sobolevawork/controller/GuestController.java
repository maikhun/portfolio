package com.example.sobolevawork.controller;

import com.example.sobolevawork.service.DocumentService;
import com.example.sobolevawork.service.ProfileService;
import com.example.sobolevawork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class GuestController {

    private final UserService userService;
    private final ProfileService profileService;
    private final DocumentService documentService;

    @RequestMapping("/user/{id}")
    public String getGuestAccessToUserProfile(@PathVariable Long id, Model model) {

        var user = userService.findUserById(id).get();

        var userProfile = profileService.getProfileByUser(user);

        boolean isTrue = (user.getNationality() == null);

        var documents = documentService.getDocumentByProfile(userProfile);

        model.addAttribute("profile", userProfile);
        model.addAttribute("nationality", isTrue);
        model.addAttribute("user", user);
        model.addAttribute("documents", documents);

        return "user-view";
    }
}
