package com.example.sobolevawork;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
@SpringBootApplication
public class portfolioTekaApplication {

    public static void main(String[] args) {

        SpringApplication.run(portfolioTekaApplication.class, args);
    }

}
