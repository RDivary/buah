package id.divary.buah.controller;

import id.divary.buah.contant.CommonConstant;
import id.divary.buah.dto.buah.BuahCreateUpdateDto;
import id.divary.buah.dto.buah.BuahDto;
import id.divary.buah.service.BuahService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/buah")
@RequiredArgsConstructor
public class BuahController {

    private final BuahService buahService;

    @GetMapping("/create")
    public String create() {
        return "buah/create-form";
    }


    @PostMapping
    public String create(@RequestParam String name) {

        buahService.create(new BuahCreateUpdateDto(name));

        return CommonConstant.BACK_TO_INDEX;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model) {
        BuahDto buah = buahService.findById(id);
        model.addAttribute("buah", buah);

        return "buah/update-form";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable String id, @RequestParam String name) {

        buahService.update(id, new BuahCreateUpdateDto(name));

        return CommonConstant.BACK_TO_INDEX;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {

        buahService.delete(id);

        return CommonConstant.BACK_TO_INDEX;
    }
}
