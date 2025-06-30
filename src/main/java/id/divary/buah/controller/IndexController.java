package id.divary.buah.controller;

import id.divary.buah.service.BuahService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class IndexController {

    private final BuahService buahService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {

        model.addAttribute("buahs", buahService.findAll());
        return "index";

    }
}
