package com.biblioteca.controller;

import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;
import com.biblioteca.service.LivroService;
import com.biblioteca.service.ReservaService;
import com.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LivroService livroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("reservas", reservaService.listarTodos());
        return "reservas/lista";
    }

    @GetMapping("/nova")
    public String novaForm(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("livros", livroService.listarTodos());
        return "reservas/form";
    }

    @PostMapping("/reservar")
    public String reservar(@RequestParam String usuarioId,
                           @RequestParam String livroId,
                           RedirectAttributes ra) {
        try {
            Usuario usuario = usuarioService.buscarPorId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
            Livro livro = livroService.buscarPorId(livroId)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
            reservaService.reservar(usuario, livro);
            ra.addFlashAttribute("mensagem", "Reserva registrada com sucesso! Válida por 7 dias.");
        } catch (Exception e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/reservas";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelar(@PathVariable String id, RedirectAttributes ra) {
        try {
            reservaService.cancelar(id);
            ra.addFlashAttribute("mensagem", "Reserva cancelada.");
        } catch (Exception e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/reservas";
    }
}
