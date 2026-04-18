package com.biblioteca.controller;

import com.biblioteca.model.Livro;
import com.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String busca) {
        if (busca != null && !busca.isBlank()) {
            model.addAttribute("livros", livroService.buscarPorTitulo(busca));
            model.addAttribute("busca", busca);
        } else {
            model.addAttribute("livros", livroService.listarTodos());
        }
        return "livros/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/form";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable String id, Model model) {
        livroService.buscarPorId(id).ifPresent(l -> model.addAttribute("livro", l));
        return "livros/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Livro livro, RedirectAttributes ra) {
        if (livro.getId() != null && !livro.getId().isBlank()) {
            livroService.atualizar(livro);
        } else {
            livro.setId(null);
            livroService.inserir(livro);
        }
        ra.addFlashAttribute("mensagem", "Livro salvo com sucesso!");
        return "redirect:/livros";
    }

    @PostMapping("/remover/{id}")
    public String remover(@PathVariable String id, RedirectAttributes ra) {
        livroService.remover(id);
        ra.addFlashAttribute("mensagem", "Livro removido com sucesso!");
        return "redirect:/livros";
    }
}
