package com.biblioteca.controller;

import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;
import com.biblioteca.service.EmprestimoService;
import com.biblioteca.service.LivroService;
import com.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LivroService livroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimoService.listarTodos());
        return "emprestimos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("livros", livroService.buscarDisponiveis());
        return "emprestimos/form";
    }

    @PostMapping("/emprestar")
    public String emprestar(@RequestParam String usuarioId,
                            @RequestParam String livroId,
                            RedirectAttributes ra) {
        try {
            Usuario usuario = usuarioService.buscarPorId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
            Livro livro = livroService.buscarPorId(livroId)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
            emprestimoService.emprestar(usuario, livro);
            ra.addFlashAttribute("mensagem", "Empréstimo registrado com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/emprestimos";
    }

    @PostMapping("/devolver/{id}")
    public String devolver(@PathVariable String id, RedirectAttributes ra) {
        try {
            emprestimoService.devolver(id);
            ra.addFlashAttribute("mensagem", "Devolução registrada com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/emprestimos";
    }
}
