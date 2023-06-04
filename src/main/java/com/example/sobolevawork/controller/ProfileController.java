package com.example.sobolevawork.controller;

import com.example.sobolevawork.model.DocumentEntity;
import com.example.sobolevawork.model.User;
import com.example.sobolevawork.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final DocumentService documentService;
    private final ProfileService profileService;
    private final AvatarService avatarService;
    private final ResourceService resourceService;
    private final CreateInviteService createInviteService;

    @RequestMapping("/profile")
    public String profile(Principal principal, Model model) throws IOException {

        // Получение профиля авторизованного пользователя
        var currentUser = userService.findUserByEmail(principal.getName());

        // Проверка - создастся ли профиль для пользователя?
        if (!profileService.createProfile(currentUser))
            return "redirect:/login";

        var profile = profileService.getProfileByUser(currentUser);

        var documents = documentService.getDocumentByProfile(profile); // Получение списка документов профиля

        boolean gotNationality = currentUser.getNationality() == null;

        model.addAttribute("documents", documents);
        model.addAttribute("nationality", gotNationality);
        model.addAttribute("profile", profile);
        model.addAttribute("user", currentUser);

        return "profile";
    }

    @GetMapping("/profile/update")
    public String getUpdateProfileDataPage() {
        return "update-information";
    }

    @PostMapping("/profile/update")
    public String updateProfileData(User user, Principal principal, Model model) {
        var currentUser = userService.findUserByEmail(principal.getName());
        userService.updateUserData(currentUser, user);
        return "redirect:/profile";
    }

    @GetMapping("/profile/upload")
    public String getUploadDocumentPage() {

        return "upload-document";
    }


    @PostMapping("/profile/upload")
    public String uploadDocumentPage(DocumentEntity documentFormData, Principal principal) {
        var currentUser = userService.findUserByEmail(principal.getName());

        var profile = profileService.getProfileByUser(currentUser);

        documentService.createDocument(documentFormData, profile);
        return "redirect:/profile";
    }

    @GetMapping("/profile/update-image")
    public String getUpdateUserAvatarPage() {
        return "update-image";
    }

    @PostMapping("/profile/update-image")
    public String updateUserAvatarPage(MultipartFile file, Principal principal, Model model) throws IOException {
        var currentUser = userService.findUserByEmail(principal.getName());
        var profile = profileService.getProfileByUser(currentUser);
        if (avatarService.createAvatar(file, profile))
            return "redirect:/profile";
        String message = "Неудачно";
        model.addAttribute("message", message);
        return "redirect:/profile";
    }

    @GetMapping("/profile/upload-doc-image/{id}")
    public String getUpdateDocumentDocPage(@PathVariable Long id, Model model) {
        var document = documentService.findById(id);
        model.addAttribute("document", document);
        return "update-doc-image";
    }

    @PostMapping("/profile/update-doc")
    public String updateDocumentDocPage(@RequestParam("documentNumber") Long id,
                                        MultipartFile file,
                                        Principal principal,
                                        Model model) throws IOException {
        var document = documentService.findById(id).get();
        if (resourceService.createDocumentResource(file, document)) {
            document.setHaveDoc(true);
            return "redirect:/profile";
        }
        document.setHaveDoc(false);
        String message = "Неудачно";
        model.addAttribute("message", message);
        return "redirect:/profile";
    }

    @PostMapping("/profile/generateLink")
    public void getUrl(@RequestParam("userId") Long userId, Principal principal, Model model) {
        Long id = userService.findUserByEmail(principal.getName()).getId();
        createInviteService.createInviteToUser(id);
    }
}
