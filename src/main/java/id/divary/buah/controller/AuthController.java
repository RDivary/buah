package id.divary.buah.controller;

import id.divary.buah.contant.CommonConstant;
import id.divary.buah.repository.UserRepository;
import id.divary.buah.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return CommonConstant.BACK_TO_INDEX;
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        return userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .map(user -> {
                    session.setAttribute("user", user);
                    return CommonConstant.BACK_TO_INDEX;
                })
                .orElse("redirect:/auth/login?error");
    }

    @GetMapping("/register")
    public String registerPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return CommonConstant.BACK_TO_INDEX;
        }
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String role, RedirectAttributes redirectAttributes) {
        try {
            userService.create(username, password, role);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("usernameError", e.getMessage());
            return "redirect:/auth/register";
        }
        redirectAttributes.addFlashAttribute("registrationSuccess", "Registration successful! You can now log in.");
        return "redirect:/auth/login";
    }
}
