package com.vibestify.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal == null)
            return "redirect:/";

        model.addAttribute("name", principal.getAttribute("display_name"));
        model.addAttribute("email", principal.getAttribute("email"));

        var images = (java.util.List<?>) principal.getAttribute("images");
        if (images != null && !images.isEmpty()) {
            var imageMap = (java.util.Map<?, ?>) images.get(0);
            model.addAttribute("image", imageMap.get("url"));
        }

        return "dashboard"; // **sin barra, sin .html**
    }
}