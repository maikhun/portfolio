package com.example.sobolevawork.controller;

import com.example.sobolevawork.service.QRCodeService;
import com.example.sobolevawork.service.UserService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequiredArgsConstructor
public class QRCodeController {

    private final QRCodeService qrCodeService;
    private final UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Сгенерировать обычный QR-код по URL-адресу
     */
    @RequestMapping(value = "/createCommonQRCode/{id}")
    public void createCommonQRCode(HttpServletResponse response,
                                   @PathVariable Long id) throws Exception {
        String url = "localhost:8080/users/" + id;
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            // Используйте инструменты для генерации QR-кода
            QRCodeService.encode(url, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * Сгенерируйте QR-код с логотипом согласно URL
     */
    @RequestMapping(value = "/createLogoQRCode")
    public void createLogoQRCode(HttpServletResponse response, String id) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
                    + "templates" + File.separator + "logo.jpg";
            // Используйте инструменты для генерации QR-кода
            QRCodeService.encode(id, logoPath, stream, true);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}
