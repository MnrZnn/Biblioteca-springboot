package com.biblioteca.controller;

import com.biblioteca.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/multas")
public class MultaController {

    @Autowired
    private MultaRepository multaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("multas", multaRepository.findAll());
        return "multas/lista";
    }

    @PostMapping("/pagar/{id}")
    public String pagar(@PathVariable String id, RedirectAttributes ra) {
        multaRepository.findById(id).ifPresent(multa -> {
            multa.setPaga(true);
            multaRepository.save(multa);
        });
        ra.addFlashAttribute("mensagem", "Multa quitada com sucesso!");
        return "redirect:/multas";
    }
}
