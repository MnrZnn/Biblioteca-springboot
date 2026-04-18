package com.biblioteca.controller;

import com.biblioteca.model.Usuario;
import com.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable String id, Model model) {
        usuarioService.buscarPorId(id).ifPresent(u -> model.addAttribute("usuario", u));
        return "usuarios/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario, RedirectAttributes ra) {
        if (usuario.getId() != null && !usuario.getId().isBlank()) {
            usuarioService.atualizar(usuario);
        } else {
            usuario.setId(null);
            usuarioService.inserir(usuario);
        }
        ra.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
        return "redirect:/usuarios";
    }

    @PostMapping("/remover/{id}")
    public String remover(@PathVariable String id, RedirectAttributes ra) {
        usuarioService.remover(id);
        ra.addFlashAttribute("mensagem", "Usuário removido com sucesso!");
        return "redirect:/usuarios";
    }
}
